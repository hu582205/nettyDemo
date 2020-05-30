package io.test.first;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 网状的服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-29
 * @see SimpleChannelInboundHandler
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	/**
	 * 收藏夹图标图标
	 */
	public static final String FAVICON_ICO = "/favicon.ico";

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest) {

			System.out.println(msg.toString());

			// 休眠8秒测试数据
			// Thread.sleep(8000);

			HttpRequest httpRequest = (HttpRequest) msg;
			System.out.println("请求方式 ------method:" + httpRequest.method().name());

			URI uri = new URI(httpRequest.uri());
			if (FAVICON_ICO.equals(uri.getPath())) {
				System.out.println("请求的是图标");
				return;
			}

			// 构建输出类容
			ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
			// 返回头信息设置
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
					content);
			HttpHeaders headers = response.headers();
			headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			headers.set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			// 对外输出流
			ctx.writeAndFlush(response);
			// ctx.channel().close();

		}

	}

}
