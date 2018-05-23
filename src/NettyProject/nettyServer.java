package NettyProject;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

public class nettyServer {
	private int port;
	
	public nettyServer(int port){
		this.port = port;
	}
	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 128)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>(){
				@Override
				public void initChannel(SocketChannel ch){
//					ch.pipeline().addLast(new nettyDiscardServer());
					ChannelPipeline c = ch.pipeline();
					c.addLast(new nettyDiscardServer())
					.addLast("stringEncoder", new StringDecoder(CharsetUtil.UTF_8))
					.addLast(new StringHandler());	
					
				
				}
			})
			  .childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture f = b.bind(port).sync();
			
			f.channel().closeFuture().sync();
			
		}
		finally{
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	public static void main(String[] args) throws Exception{
		/**
		 * 
		 */
		int port = 8000;
		new nettyServer(port).run();
		
	}

}
