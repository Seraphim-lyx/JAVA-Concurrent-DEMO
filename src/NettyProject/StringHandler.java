package NettyProject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class StringHandler extends SimpleChannelInboundHandler<String> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String message)
			throws Exception {
		// StringBuffer sb = new StringBuffer();
		// ctx.fireChannelRead(message); //next channel
	}



}
