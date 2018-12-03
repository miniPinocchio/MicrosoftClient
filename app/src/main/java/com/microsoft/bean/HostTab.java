package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/12/3.
 * @email liu594545591@126.com
 * @introduce
 */
public class HostTab {
    private int title ;
    private int icon ;
    private Class fragment ;

    public HostTab(int title, int icon, Class fragment) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
