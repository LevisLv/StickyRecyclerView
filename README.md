## 前言
网络上千篇一律的做法是在 RecyclerView 的(0, 0)坐标位置覆盖一个与 item 的 title 相同的布局(以下称 cur 布局)，添加滑动监听，当 RecyclerView 带有 title 的 item 上滑出屏幕时，该布局也随之上滑(setTranslationY)，当 title 部分刚好滑出屏幕时，该布局恢复原来的位置，内容替换成 title 内容(下滑进入屏幕同理)。但这种方法必须布局背景无透明度才能实现，否则在滑动过程中能看到 RecyclerView 的内容，在页面有透明背景的需求下就不适用了。

本文将介绍如何自定义一个带透明底 title，悬浮吸顶式的 RecyclerView，先上效果图：

![StickyRecyclerView](https://github.com/LevisLv/LevisLv.github.io/blob/master/2018/09/17/title%20%E5%BA%95%E9%83%A8%E9%80%8F%E6%98%8E%E7%9A%84%E6%82%AC%E6%B5%AE%E5%90%B8%E9%A1%B6%E5%BC%8F%20RecyclerView/StickyRecyclerView.gif)

## 基本思路
在传统的做法上加上一个布局(在 RecyclerView 上面(above)也添加一个与 item 的 title 相同的布局(以下称 pre 布局))，添加滑动监听，当 RecyclerView 带有 title 的 item 上滑出屏幕时，cur 和 pre 布局同时随之上滑(setTranslationY)，当 title 部分刚好滑出屏幕时，pre 布局恢复原来的位置，内容替换成 title 内容，cur 布局隐藏(下滑进入屏幕同理)。
有一点需要注意，当列表无数据时，cur 布局需要隐藏。
