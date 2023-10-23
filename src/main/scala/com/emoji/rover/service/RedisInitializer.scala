package com.emoji.rover.service

import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

class RedisInitializer extends Serializable {

  def getJedisInstance: Jedis = {
    val jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379)
    val jedis = jedisPool.getResource
    jedis
  }

}
