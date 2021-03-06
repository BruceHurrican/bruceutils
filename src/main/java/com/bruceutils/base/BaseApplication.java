/*
 * BruceHurrican
 * Copyright (c) 2016.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet, only to use as a learning exchanges.
 *   And where any person can download and use, but not for commercial purposes.
 *   Author does not assume the resulting corresponding disputes.
 *   If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruceutils.base;

import android.app.Activity;
import android.app.Application;

import com.bruceutils.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BruceHurrican on 2016/4/12.
 */
public class BaseApplication extends Application {
    private List<Activity> container;

    @Override
    public void onCreate() {
        super.onCreate();
        //        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
//        crashHandler.initActivityContainer(container);
        container = new ArrayList<>(5);
    }

    public void addActivity(Activity activity) {
        if (null != activity) {
            container.add(activity);
            LogUtils.i("加入的activity：" + activity.getLocalClassName());
        } else {
            LogUtils.e("加入的activity为空");
        }
    }

    public void delActivity(Activity activity) {
        if (null != activity) {
            LogUtils.i("销毁的activity：" + activity.getLocalClassName());
            container.remove(activity);
        } else {
            LogUtils.e("待删除的activity为空");
        }
        if (null != container && container.size() == 0) {
            exitApp();
        }
    }

    public void exitApp() {
        if (container == null || container.size() == 0) {
            LogUtils.i("activity容器已经清空");
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }
        for (Activity activity : container) {
            LogUtils.i("程序有序退出中，当前activity：" + activity.getLocalClassName());
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
