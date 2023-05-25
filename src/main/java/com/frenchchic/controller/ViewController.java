package com.frenchchic.controller;

import com.frenchchic.view.VueJetable;

public abstract class ViewController {
    private Session session;
    private VueJetable vueParent;

    public VueJetable getVueParent() {
        return vueParent;
    }

    public void setVueParent(VueJetable vueParent) {
        this.vueParent = vueParent;
    }
}
