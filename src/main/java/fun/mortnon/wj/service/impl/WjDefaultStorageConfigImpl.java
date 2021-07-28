package fun.mortnon.wj.service.impl;

import fun.mortnon.wj.model.AccessToken;
import fun.mortnon.wj.service.WjStorageConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongfangzan
 * @date 27.7.21 5:24 下午
 */
public class WjDefaultStorageConfigImpl implements WjStorageConfig {

    /** token */
    @Getter
    private AccessToken accessToken;

    /** 过期时间 */
    @Getter
    @Setter
    private long accessTokenExpiresTime;

    /** token刷新锁 */
    private final ReentrantLock tokenRefreshLock = new ReentrantLock();


    public WjDefaultStorageConfigImpl() {
    }

    @Override
    public void setAccessToken(AccessToken accessToken) {
        // this锁刷新token
        tokenRefreshLock.lock();
        try {
            this.accessTokenExpiresTime = System.currentTimeMillis() + accessToken.getExpiresIn() * 1000L;
            this.accessToken = accessToken;
        } finally {
            tokenRefreshLock.unlock();
        }
    }

    @Override
    public void expireAccessToken() {
        this.accessToken = null;
        this.accessTokenExpiresTime = 0L;
    }
}
