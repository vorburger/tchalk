require 'vertx'
@v, @eb = Vertx, Vertx::EventBus

# Redis helper
def redis(command, args, &callback)
  @eb.send 'tchalk.redis', {'command' => command, 'args' => args } do |reply|
    callback.call reply.body if callback
  end
end

# Builds the redis key for a channel
def yoKey!(channel, msg = nil)
  if channel
    'channels:'+channel+':yos'
  else
    err = 'Channel was not specified'
    if msg
      response = { 'status' => 'error', 'message' => err}
      msg.reply response
    end
    raise err
  end
end

# Stores a Yo
SINK='tchalk.yo.sink'
@eb.register_handler SINK do |msg|
  yo = msg.body
  key = yoKey!(yo['channel'], msg)
  redis 'lpush', [key, yo] do |reply|
      msg.reply reply
  end
  # Keep inactive conversations for one month
  redis 'expire', [key, @v.config['yo.expiration'] || 24*3600*30]
  # Cleanup yos if too big
  redis 'llen', [key] do |reply|
    redis 'rpop', [key] if reply['value'] > (@v.config['yo.max.length'] || 10000)
  end
end

# Get Yos
SOURCE='tchalk.yo.source'
@eb.register_handler SOURCE do |msg|
  channel = msg.body['channel']
  limit = msg.body['limit'] || @v.config['channel.default.length'] || 100
  redis 'lrange', [ yoKey!(channel, msg), 0, limit] do |reply|
    result = []
    reply['value'].each do |x|
      result.push JSON.parse x
    end
    msg.reply({ 'value' => result })
  end
end
