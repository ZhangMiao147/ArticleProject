package com.zm.playaudiotest;

import android.media.MediaPlayer;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: lhy
 * Date: 2019/3/8
 */
public class AudioPlayManager {

    private static volatile AudioPlayManager INSTANCE;
    private MediaPlayer mPlayer;
    private Map<String, PlayerListener> mListenerMap;
    private String mPlayingPath;
    private Map<String, Boolean> mLoadingMap;
    int duration = 0;

    public static AudioPlayManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AudioPlayManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AudioPlayManager();
                }
            }
        }
        return INSTANCE;
    }

    public boolean isPlayingPath(String path) {
        return !TextUtils.isEmpty(mPlayingPath) && mPlayingPath.equals(path) && mPlayer.isPlaying();
    }

    public void start(final String filePath, PlayerListener listener) {
        if (!TextUtils.isEmpty(mPlayingPath)) {
            stop(mPlayingPath);
        }
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
        if (mListenerMap == null) {
            mListenerMap = new HashMap<>();
        }
        mListenerMap.put(filePath, listener);
        if (mLoadingMap == null) {
            mLoadingMap = new HashMap<>();
        }
        mLoadingMap.put(filePath, true);
        mPlayingPath = filePath;

        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mLoadingMap != null && mLoadingMap.containsKey(filePath)) {
                    mLoadingMap.put(filePath, true);
                }
                duration = mPlayer.getDuration();
                mPlayer.start();
                if (mListenerMap != null && mListenerMap.containsKey(filePath)) {
                    PlayerListener playerListener = mListenerMap.get(filePath);
                    if (playerListener != null) {
                        playerListener.onPrepared();
                    }
                }
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mLoadingMap != null && mLoadingMap.containsKey(filePath)) {
                    mLoadingMap.put(filePath, true);
                }
                if (mListenerMap != null && mListenerMap.containsKey(filePath)) {
                    PlayerListener playerListener = mListenerMap.get(filePath);
                    if (playerListener != null) {
                        playerListener.onCompleted();
                    }
                    mListenerMap.remove(filePath);
                }
                release(filePath);
                mPlayingPath = "";
            }
        });

        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (mLoadingMap != null && mLoadingMap.containsKey(filePath)) {
                    mLoadingMap.put(filePath, true);
                }
                if (mListenerMap != null && mListenerMap.containsKey(filePath)) {
                    PlayerListener playerListener = mListenerMap.get(filePath);
                    if (playerListener != null) {
                        playerListener.onError();
                    }
                    mListenerMap.remove(filePath);
                }
                release(filePath);
                mPlayingPath = "";
                return false;
            }
        });
        try {
            mPlayer.setDataSource(filePath);
        } catch (IOException e) {
            if (mLoadingMap != null && mLoadingMap.containsKey(filePath)) {
                mLoadingMap.put(filePath, true);
            }
            if (mListenerMap != null && mListenerMap.containsKey(filePath)) {
                PlayerListener playerListener = mListenerMap.get(filePath);
                if (playerListener != null) {
                    playerListener.onError();
                }
                mListenerMap.remove(filePath);
            }
            release(filePath);
            mPlayingPath = "";
            e.printStackTrace();
        }
        mPlayer.prepareAsync();
    }

    public void stop(String filePath) {
        if (mLoadingMap != null && mLoadingMap.containsKey(filePath)) {
            mLoadingMap.put(filePath, true);
        }
        if (mListenerMap != null && mListenerMap.containsKey(filePath)) {
            PlayerListener playerListener = mListenerMap.get(filePath);
            if (playerListener != null) {
                playerListener.onCompleted();
            }
            mListenerMap.remove(filePath);
        }
        mPlayingPath = "";
        release(filePath);
    }

    public int getDurationButNotPlay(String path) {
        return duration;
    }

    public boolean isPlaying() {
        if (mPlayer != null)
            return mPlayer.isPlaying();
        return false;
    }

    public void release(String filePath) {
        try {
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer.reset();
                mPlayer.release();
                mPlayer = null;
            }
            if (mListenerMap != null && mListenerMap.containsKey(filePath)) {
                mListenerMap.get(filePath).onCompleted();
                mListenerMap.remove(filePath);
            }
            if (mLoadingMap != null && mListenerMap.containsKey(filePath)) {
                mLoadingMap.remove(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        try {
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer.reset();
                mPlayer.release();
                mPlayer = null;
            }
            if (mListenerMap != null && mListenerMap.size() > 0) {
                Set<String> keySet = mListenerMap.keySet();
                for (String key : keySet) {
                    mListenerMap.get(key).onCompleted();
                }
                mListenerMap.clear();
            }
            if (mLoadingMap != null) {
                mLoadingMap.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface PlayerListener {
        void onPrepared();

        void onCompleted();

        void onError();
    }

}
