package NettyProject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ByteProcessor;
import io.netty.util.ReferenceCountUtil;

public class nettyDiscardServer extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf)msg;
		
		try{
			while(in.isReadable()){
				System.out.println((char)in.readByte());
				System.out.flush();
//				System.out.println(in.forEachByte(ByteProcessor.FIND_NUL));
			}
				

		} finally{
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
