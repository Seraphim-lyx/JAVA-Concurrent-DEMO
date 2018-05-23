package NettyProject;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class nettyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>(){
				@Override
				public void initChannel(SocketChannel ch){
					ChannelPipeline cp = ch.pipeline();
					cp.addLast(new NettyClientHandler());
				}
			});
		}finally{
			
		}
	}

}
