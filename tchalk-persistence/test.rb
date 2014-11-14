require 'vertx'
@v, @eb = Vertx, Vertx::EventBus
@v.deploy_module 'io.vertx~mod-redis~1.1.4', { 'address' => 'tchalk.redis' }
@v.deploy_module 'ch.vobos.tchalk~tchalk-persistence~0.0.1',
                 { 'yo.expiration' => 10000,
                   'yo.max.length' => 500 }
@v.set_timer 2000 do
  yo = {
      'channel' => 'hello',
      'time' => Time.now
  }
  @eb.send 'tchalk.yo.sink', yo do |reply|
    puts reply.body
  end
  @eb.send 'tchalk.yo.source',
           { 'channel' => 'hello', 'limit' => 5 } do |reply|
    puts reply.body
  end
end
