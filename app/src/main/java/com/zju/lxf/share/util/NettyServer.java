package com.zju.lxf.share.util;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap sb = new ServerBootstrap();
        sb.group(group).channel(NioServerSocketChannel.class);
    }
}
