package com.ctrip.xpipe.redis.proxy.session.state;

import com.ctrip.xpipe.redis.proxy.Session;
import com.ctrip.xpipe.redis.proxy.session.AbstractSession;
import com.ctrip.xpipe.redis.proxy.session.SessionState;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;

/**
 * @author chen.zhu
 * <p>
 * May 13, 2018
 */
public class SessionClosing extends AbstractSessionState {

    public SessionClosing(Session session) {
        super(session);
    }

    @Override
    protected SessionState doNextAfterSuccess() {
        return new SessionClosed(session);
    }

    @Override
    protected SessionState doNextAfterFail() {
        return this;
    }

    @Override
    public ChannelFuture tryWrite(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("No write, Session closing");
    }

    @Override
    public void disconnect() {
        ((AbstractSession)session).doDisconnect();
    }

    @Override
    public String name() {
        return "Session-Closing";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
