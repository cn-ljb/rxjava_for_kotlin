# Kotlin Android环境搭建

Google官方已在[android studio 3.0 Preview](https://developer.android.com/studio/preview/index.html) 版本中支持Kotlin ， 对于现役[20170612]正式2.3等版本也提供以插件的形式来搭建开发环境。

官方教程：[http://kotlinlang.org/docs/tutorials/kotlin-android.html](http://kotlinlang.org/docs/tutorials/kotlin-android.html "http://kotlinlang.org/docs/tutorials/kotlin-android.html")

简述流程：

**1、插件安装方式**

![](http://i.imgur.com/s5IVW40.png)

**2、java代码转kotlin**

find-action -> convertjava file to kotlin file

![](http://i.imgur.com/L47dDPr.png)

**3、配置gradle环境**

诺这是你第一次使用Kotlin代码，Studio会提醒还未配置Kotlin相关参数,点击右上角 configure 配置即可

![](http://i.imgur.com/J53Cm7v.png)

或者**手动配置**如下信息：

**（1）项目中 build.gradle 文件**

	buildscript {
	    ext.kotlin_version = '1.1.2-4' \\定义kotlin版本变量

	    dependencies {
	        ...
	        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
	        ...
	    }
	}

	...

**（2）主Module中 build.gradle 文件**

	apply plugin: 'com.android.application'
	apply plugin: 'kotlin-android'

	...

	dependencies {
	     ...
	    compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
	}

	...

Kotlin有一个相当小的运行时文件：大约是859KB（从1.1.2-2开始）。这意味着Kotlin对.apk文件大小会增加一点。


## Kotlin对Android平台的额外扩展

### 1、kotlin-android-extensions [官方介绍](http://kotlinlang.org/docs/tutorials/android-plugin.html)

在moudle的 build.gradle 文件中添加以下脚本引入

	apply plugin: 'kotlin-android-extensions'


主要功能：

>（1）控件导入

>（2）支持Activty扩展方法、扩展属性



### 2、anko [官方介绍](https://github.com/kotlin/anko)

如果你要编写 100% Kotlin 代码的App，那这个库必不可少

主要功能：

有点多，还是直接看官方介绍更实在


