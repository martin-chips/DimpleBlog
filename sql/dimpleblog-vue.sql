/*
SQLyog Enterprise v13.1.1 (64 bit)
MySQL - 8.0.17 : Database - dimpleblog-vue
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dimpleblog-vue` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dimpleblog-vue`;

/*Table structure for table `bg_blog` */

DROP TABLE IF EXISTS `bg_blog`;

CREATE TABLE `bg_blog` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' id ',
                           `category_id` bigint(20) DEFAULT NULL COMMENT ' 分类id ',
                           `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT ' 文章标题 ',
                           `summary` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT ' 摘要 ',
                           `header_img_type` int(11) NOT NULL,
                           `header_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT ' 封面图片地址 ',
                           `html_content` longtext,
                           `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT ' 正文内容 ',
                           `status` tinyint(4) DEFAULT NULL COMMENT ' 文章状态,\r\n    1表示已经发表,\r\n    2表示草稿箱 ',
                           `comment` tinyint(4) DEFAULT NULL COMMENT ' 是否允许评论,\r\n    1表示允许,\r\n    0表示不允许 ',
                           `support` tinyint(4) DEFAULT '0' COMMENT ' 推荐 ',
                           `weight` bigint(20) DEFAULT '1' COMMENT ' 权重 ',
                           `like` bigint(20) DEFAULT '0' COMMENT ' 点赞数 ',
                           `click` bigint(20) DEFAULT '0' COMMENT ' 点击数 ',
                           `create_by` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `update_by` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `update_time` datetime DEFAULT NULL,
                           `delete_time` datetime DEFAULT NULL,
                           `delete_by` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博客表';

/*Data for the table `bg_blog` */

insert  into `bg_blog`(`id`,`category_id`,`title`,`summary`,`header_img_type`,`header_img`,`html_content`,`content`,`status`,`comment`,`support`,`weight`,`like`,`click`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_time`,`delete_by`) values
(21,939,'《吐血整理》-顶级程序员工具集21','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',' < h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,0,1,4,144,159,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(22,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）22','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'https://s2.ax1x.com/2019/07/31/etHSHS.jpg','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！</p>\n','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,1,5,46,62,'admin','2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(23,939,'《吐血整理》-顶级程序员工具集23','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'https://s2.ax1x.com/2019/07/31/etHSHS.jpg','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,1,4,73,57,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(24,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）24','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'https://s2.ax1x.com/2019/07/31/etHSHS.jpg','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！<','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,1,5,91,37,'admin','2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(25,939,'《吐血整理》-顶级程序员工具集25','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,1,4,54,47,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(26,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！<','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,0,5,38,18,'admin','2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(27,939,'《吐血整理》-顶级程序员工具集','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,0,4,52,46,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(28,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！<','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,0,5,38,17,'admin','2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(29,939,'《吐血整理》-顶级程序员工具集','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,0,4,53,53,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(30,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！</p>\n','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,0,5,38,24,'admin','2019-11-28 14:44:31','admin','2019-12-11 16:17:37',NULL,NULL),
(31,939,'《吐血整理》-顶级程序员工具集','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,0,4,52,46,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(32,939,'《吐血整理》-顶级程序员工具集','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,0,4,52,39,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(33,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！<','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,0,0,5,38,17,'admin','2019-11-28 14:44:31','admin','2019-11-28 23:18:33',NULL,NULL),
(34,939,'《吐血整理》-顶级程序员工具集','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,0,4,52,38,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(35,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！<','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,0,5,38,15,'admin','2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(36,939,'《吐血整理》-顶级程序员工具集','你知道的越多，你不知道的越多\n\n点赞再看，养成习惯\n\nGitHub上已经开源 https://github.com/JavaFamily 有一线大厂面试点脑图、个人联系方式，欢迎Star和指教',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"前言\">前言</h1>\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\n<p>千万不要白嫖，真香警告⚠️。</p>\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\n<h1 id=\"正文\">正文</h1>\n<p>既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n<strong>Tip</strong>：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。</p>\n<p>我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。</p>\n<h2 id=\"概要设计\">概要设计</h2>\n<p>概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。</p>\n<p>很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？</p>\n<p>问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。</p>\n<h3 id=\"工具：xmindprocesson\">工具：Xmind/ProcessOn</h3>\n<p>Xmind官网地址： <a href=\"https://www.xmind.cn\">https://www.xmind.cn</a>\nProcessOn在线作图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<h2 id=\"详细设计\">详细设计</h2>\n<p>小伙伴又要问了啥是详细设计呀帅丙？</p>\n<p>诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。</p>\n<p>我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。</p>\n<p>看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？</p>\n<p>那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。</p>\n<p>详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：<a href=\"https://www.processon.com\">https://www.processon.com</a></p>\n<blockquote>\n<p>总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全</p>\n</blockquote>\n<h1 id=\"研发\">研发</h1>\n<p>这个关键了：工欲善其事，必先利其器</p>\n<p>想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。</p>\n','# 前言\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\n\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\n\n千万不要白嫖，真香警告⚠️。\n\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\n\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\n\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\n\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\n# 正文\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\n\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\n## 概要设计\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\n\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\n\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\n### 工具：Xmind/ProcessOn\nXmind官网地址： https://www.xmind.cn\nProcessOn在线作图地址：https://www.processon.com\n\n## 详细设计\n小伙伴又要问了啥是详细设计呀帅丙？\n\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\n\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\n\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\n\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\n\n详细设计的工具我用的就是在线作图神器：ProcessOn\n在线画图地址：https://www.processon.com\n\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\n\n# 研发\n这个关键了：工欲善其事，必先利其器\n\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\n\n',1,1,0,4,52,38,'admin','2019-11-28 14:03:52','admin','2019-11-28 14:10:53',NULL,NULL),
(37,940,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）','不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落都精彩 磨难用双手推开 全力去对待 豁出去才痛快 看我和命运 谁厉害',0,'http://images.bianxiaofeng.com/7519f60723840cd3a7d1ee385a46deaf','<h1 id=\"1简介\">1.简介</h1>\n<p>　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥</p>\n<p>计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。</p>\n<p>　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。</p>\n<h1 id=\"2-练习场景：\">2. 练习场景：</h1>\n<p>　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。</p>\n<h1 id=\"3-思路拆分：\">3. 思路拆分：</h1>\n<ol>\n<li><p>首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。</p>\n</li>\n<li><p>找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。</p>\n</li>\n<li><p>循环打印字典或列表中内容，Python中用 for 语句实现。</p>\n</li>\n</ol>\n<h1 id=\"4技术角度实现相关方法：\">4.技术角度实现相关方法：</h1>\n<ol>\n<li><p>查看页面的源代码，在Selenium中有driver.page_source 这个方法得到</p>\n</li>\n<li><p>Python中利用正则，需要导入re模块</p>\n</li>\n<li><p>将字段通过下列代码打印出来</p>\n</li>\n</ol>\n<pre><code>for email in emails :\n\n          print email\n</code></pre><h2 id=\"41-代码实现\">4.1 代码实现</h2>\n<p>想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:</p>\n<h2 id=\"42-参考代码：\">4.2 参考代码：</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;http://home.baidu.com/contact.html&quot;)\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r&#39;[\\w]+@[\\w\\.-]+&#39;, doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n</code></pre><h2 id=\"43-解释说明：\">4.3 解释说明：</h2>\n<p>在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。</p>\n<h2 id=\"44-运行结果：\">4.4 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"5利用id定位元素\">5.利用ID定位元素</h1>\n<p>　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。</p>\n<pre><code>  下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。</code></pre><h2 id=\"51-代码实现\">5.1 代码实现</h2>\n<p>我们新建一个test_baidu_id.py文件，输入如下代码：</p>\n<h2 id=\"52-参考代码\">5.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_id(&quot;kw&quot;)\n    print (&#39;test pass: ID found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><p>这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。</p>\n<h2 id=\"53-运行结果：\">5.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"6-利用tag-name定位元素\">6. 利用tag name定位元素</h1>\n<p>   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。</p>\n<pre><code>  什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n  上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个</code></pre><p>form来作为我们tag name。</p>\n<h2 id=\"61-代码实现\">6.1 代码实现</h2>\n<p>看看如何写定位form这个元素的脚本：</p>\n<h2 id=\"62-参考代码\">6.2 参考代码</h2>\n<pre><code># coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n&#39;&#39;&#39;\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n&#39;&#39;&#39;\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(&quot;https://www.baidu.com&quot;)\ntry:\n    driver.find_element_by_tag_name(&quot;form&quot;)\n    print (&#39;test pass: tag name found&#39;)\nexcept Exception as e:\n    print (&quot;Exception found&quot;, format(e))\n\ndriver.quit()\n</code></pre><h2 id=\"63-运行结果：\">6.3 运行结果：</h2>\n<p>运行代码后，控制台打印如下图的结果</p>\n<h1 id=\"7-小结\">7. 小结</h1>\n<p>总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(&quot;form&quot;) # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。</p>\n<p>您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！<','# 1.简介\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\n\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\n\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\n\n# 2. 练习场景：\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\n\n# 3. 思路拆分：\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\n\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\n\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\n\n# 4.技术角度实现相关方法：\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\n\n2. Python中利用正则，需要导入re模块\n\n3. 将字段通过下列代码打印出来\n\n```\nfor email in emails :\n\n          print email\n\n```\n\n## 4.1 代码实现\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\n\n\n\n## 4.2 参考代码：\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\nimport re\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"http://home.baidu.com/contact.html\")\n# 得到页面源代码\ndoc = driver.page_source\nemails = re.findall(r\'[\\w]+@[\\w\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\n# 循环打印匹配的邮箱\nfor email in emails:\n    print(email)\n\n```\n\n## 4.3 解释说明：\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\n\n## 4.4 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n\n# 5.利用ID定位元素\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\n\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\n\n## 5.1 代码实现\n我们新建一个test_baidu_id.py文件，输入如下代码：\n\n\n\n## 5.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_id(\"kw\")\n    print (\'test pass: ID found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n这里，我们通过try except语句块来进行测试断言，这个在实际自动化测试脚本开发中，经常要用到处理异常。本文，我们学习了可以利用find_element_by_id（）方法来定位网页元素对象。\n\n## 5.3 运行结果：\n\n运行代码后，控制台打印如下图的结果\n\n\n\n# 6. 利用tag name定位元素\n   前边介绍了如何通过元素的id值来定位web元素，本文介绍如何通过tag name来定位元素。个人认为，通过tag name来定位还是有很大缺陷，定位不够精确。主要是tag name有很多重复的，造成了选择tag name来定位页面元素不准确，所以使用这个方法定位web元素的机会很少。\n\n      什么是tag name? 还是以百度首页搜索输入框，在火狐浏览器，右键，通过firepath，检查元素，看下图：\n\n\n\n      上面图片中红色圈选区域的标签名称都是tag name；实际上我们目标元素是输入框，应该是input这个tag name，在图中蓝色高亮区域。但是如果只是通过input这个tag name来定位，发现上面有很多input的选项。所以我们扩大节点的参照选择，我们选择上面这个\n\nform来作为我们tag name。\n\n## 6.1 代码实现\n看看如何写定位form这个元素的脚本：\n\n\n\n## 6.2 参考代码\n```\n# coding=utf-8\n\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\n\n# 2.注释：包括记录创建时间，创建人，项目名称。\n\'\'\'\nCreated on 2019-11-28\n@author: 北京-宏哥   QQ交流群：705269076\nProject: python+ selenium自动化测试练习篇\n\'\'\'\n\n# 3.导入模块\nfrom selenium import webdriver\n\ndriver = webdriver.Chrome()\ndriver.maximize_window()\ndriver.implicitly_wait(6)\n\ndriver.get(\"https://www.baidu.com\")\ntry:\n    driver.find_element_by_tag_name(\"form\")\n    print (\'test pass: tag name found\')\nexcept Exception as e:\n    print (\"Exception found\", format(e))\n\ndriver.quit()\n\n```\n\n## 6.3 运行结果：\n运行代码后，控制台打印如下图的结果\n\n\n# 7. 小结\n总结：本文介绍了webdriver 八大定位元素方法中的driver.find_element_by_tag_name(\"form\") # form是tag name从实际项目中自动化脚本开发来看，使用这个方法定位元素的机会比较少，知道有这么一种方法就好了。\n\n \n\n您的肯定就是我进步的动力。如果你感觉还不错，就请鼓励一下吧！记得点波 推荐 不要忘记哦！！！',1,1,0,5,62,53,'admin','2018-11-28 14:44:31',NULL,NULL,NULL,NULL);

/*Table structure for table `bg_blog_tag` */

DROP TABLE IF EXISTS `bg_blog_tag`;

CREATE TABLE `bg_blog_tag` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `blog_id` bigint(20) DEFAULT NULL,
                               `tag_id` bigint(20) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='blog和tag的关联表';

/*Data for the table `bg_blog_tag` */

insert  into `bg_blog_tag`(`id`,`blog_id`,`tag_id`) values
(14,21,7),
(15,21,8),
(16,21,9),
(17,22,10),
(18,22,11),
(19,22,12);

/*Table structure for table `bg_category` */

DROP TABLE IF EXISTS `bg_category`;

CREATE TABLE `bg_category` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `title` varchar(128) DEFAULT NULL COMMENT '分类名称',
                               `description` varchar(256) DEFAULT NULL COMMENT '描述',
                               `support` tinyint(4) DEFAULT NULL COMMENT '是否推荐',
                               `create_by` varchar(128) DEFAULT NULL,
                               `create_time` datetime DEFAULT NULL,
                               `update_by` varchar(128) DEFAULT NULL,
                               `update_time` datetime DEFAULT NULL,
                               `delete_by` varchar(128) DEFAULT NULL,
                               `delete_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=942 DEFAULT CHARSET=utf8 COMMENT='分类表';

/*Data for the table `bg_category` */

insert  into `bg_category`(`id`,`title`,`description`,`support`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(939,'工具集合','你知道的越多，你不知道的越多',1,'admin','2019-11-28 13:54:18',NULL,NULL,NULL,NULL),
(940,'教程分类','自动化测试相关教程1',1,'admin','2019-11-28 14:28:57',NULL,NULL,NULL,NULL),
(941,'111111111','少时诵诗书所所所所所所所所所所',1,'admin','2019-12-17 10:21:05',NULL,NULL,NULL,NULL);

/*Table structure for table `bg_comment` */

DROP TABLE IF EXISTS `bg_comment`;

CREATE TABLE `bg_comment` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `nick_name` varchar(128) DEFAULT NULL COMMENT '昵称',
                              `email` varchar(128) DEFAULT NULL COMMENT 'Email地址',
                              `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址\n',
                              `location` varchar(128) DEFAULT NULL COMMENT '地理位置',
                              `os` varchar(128) DEFAULT NULL COMMENT '系统',
                              `browser` varchar(128) DEFAULT NULL COMMENT '浏览器',
                              `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论的id',
                              `qq_num` varchar(128) DEFAULT NULL COMMENT 'QQ号码',
                              `avatar` varchar(256) DEFAULT NULL COMMENT '头像地址',
                              `page_id` bigint(20) DEFAULT NULL COMMENT '页面ID',
                              `url` varchar(128) DEFAULT NULL COMMENT '页面的URL\n',
                              `display` tinyint(4) DEFAULT NULL COMMENT '1表示显示,0表示不显示',
                              `good` bigint(20) DEFAULT '0' COMMENT '点赞\n',
                              `bad` bigint(20) DEFAULT '0' COMMENT '踩',
                              `content` varchar(2048) DEFAULT NULL COMMENT '评论内容',
                              `create_by` varchar(128) DEFAULT NULL,
                              `create_time` datetime DEFAULT NULL,
                              `update_by` varchar(128) DEFAULT NULL,
                              `update_time` datetime DEFAULT NULL,
                              `delete_by` varchar(128) DEFAULT NULL,
                              `delete_time` datetime DEFAULT NULL,
                              `html_content` longtext,
                              `reply` tinyint(4) DEFAULT NULL,
                              `admin_reply` tinyint(4) DEFAULT NULL,
                              `reply_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='评论表';

/*Data for the table `bg_comment` */

insert  into `bg_comment`(`id`,`nick_name`,`email`,`ip`,`location`,`os`,`browser`,`parent_id`,`qq_num`,`avatar`,`page_id`,`url`,`display`,`good`,`bad`,`content`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`html_content`,`reply`,`admin_reply`,`reply_id`) values
(4,'sda ','asdasdasd@sn.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,21,'http://localhost/article/21',NULL,1,2,'fdsfdsfdsf',NULL,'2019-12-03 15:12:07',NULL,NULL,NULL,NULL,'<p>fdsfdsfdsf</p>\n',1,0,NULL),
(5,'2222','222@ss.com','192.168.13.145','内网IP','Windows 10','Firefox 7',NULL,'7265677',NULL,37,'http://localhost/article/37',NULL,1,1,'ddddd',NULL,'2019-12-09 10:30:26',NULL,NULL,NULL,NULL,'<p>ddddd</p>\n',1,0,NULL),
(6,'ddddddd','dddddd@ddd.com','192.168.13.145','内网IP','Windows 10','Firefox 7',NULL,'dddddd',NULL,37,'http://localhost/article/37',NULL,0,0,'ddddd',NULL,'2019-12-09 10:38:44',NULL,NULL,NULL,NULL,'<p>ddddd</p>\n',1,0,NULL),
(7,'dddd','dddddddddd','192.168.13.145','内网IP','Windows 10','Firefox 7',6,'dddd',NULL,37,'http://localhost/article/37#7-%E5%B0%8F%E7%BB%93',NULL,0,0,'ddddddddddd',NULL,'2019-12-09 10:41:21',NULL,NULL,NULL,NULL,'<p>ddddddddddd</p>\n',1,0,6),
(8,'11111111111','1111111111111111','192.168.13.145','内网IP','Windows 10','Firefox 7',NULL,'1111111111',NULL,-1000,'http://localhost/leaveComment',NULL,0,0,'11111',NULL,'2019-12-09 10:44:20',NULL,NULL,NULL,NULL,'<p>11111</p>\n',1,0,NULL),
(9,'1111111111111','111111111111111','192.168.13.145','内网IP','Windows 10','Firefox 7',8,'1111111111',NULL,-1000,'http://localhost/leaveComment',NULL,0,0,'11111111111111111111111111111',NULL,'2019-12-09 10:44:25',NULL,NULL,NULL,NULL,'<p>11111111111111111111111111111</p>\n',1,0,8),
(10,'1111111111111','111111111111111111','192.168.13.145','内网IP','Windows 10','Firefox 7',NULL,'11111111111',NULL,-1000,'http://localhost/leaveComment',NULL,0,0,'111111111111111111111111111111',NULL,'2019-12-09 10:44:30',NULL,NULL,NULL,NULL,'<p>111111111111111111111111111111</p>\n',1,0,NULL),
(11,'111111','2222222222222222','192.168.13.145','内网IP','Windows 10','Firefox 7',NULL,'2222222222222',NULL,-1000,'http://localhost/leaveComment',NULL,0,0,NULL,NULL,'2019-12-09 10:46:04',NULL,NULL,NULL,NULL,NULL,1,0,NULL),
(12,'111111111111','111111111111','192.168.13.145','内网IP','Windows 10','Firefox 7',4,'1111111',NULL,21,'http://localhost/article/21',NULL,0,0,'1111111111111111111',NULL,'2019-12-09 14:17:47',NULL,NULL,NULL,NULL,'<p>1111111111111111111</p>\n',1,0,4),
(13,'11111111111111','111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',7,'111111111111',NULL,37,'http://localhost/leaveComment',NULL,0,0,'111111111111111111111111111111',NULL,'2019-12-09 15:15:39',NULL,NULL,NULL,NULL,'<p>111111111111111111111111111111</p>\n',1,0,7),
(14,'22222222222222222222','2222222222222222222','192.168.13.145','内网IP','Windows 10','Chrome 8',7,'2',NULL,37,'http://localhost/article/37',NULL,0,0,'2222222222222222222222222',NULL,'2019-12-09 15:16:04',NULL,NULL,NULL,NULL,'<p>2222222222222222222222222</p>\n',1,0,7),
(15,'111111111111111','11111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',14,'11',NULL,37,'http://localhost/article/37',NULL,0,0,'1111111111111111111111',NULL,'2019-12-09 15:16:53',NULL,NULL,NULL,NULL,'<p>1111111111111111111111</p>\n',1,0,14),
(16,'2222222222222222','222222222222222','192.168.13.145','内网IP','Windows 10','Chrome 8',7,'222',NULL,37,'http://localhost/article/37',NULL,0,0,'222222222222222222222',NULL,'2019-12-09 15:18:13',NULL,NULL,NULL,NULL,'<p>222222222222222222222</p>\n',1,0,7),
(17,'222222222222222','2222222222222222','192.168.13.145','内网IP','Windows 10','Chrome 8',7,'2222',NULL,37,'http://localhost/',NULL,0,0,'22222222222222222',NULL,'2019-12-09 17:05:52',NULL,NULL,NULL,NULL,'<p>22222222222222222</p>\n',1,0,7),
(18,'6666666666666','333333333333','192.168.13.145','内网IP','Windows 10','Chrome 8',17,'6666666',NULL,37,'http://localhost/article/37',NULL,0,0,'		666666',NULL,'2019-12-09 17:06:15',NULL,NULL,NULL,NULL,'<pre><code>    666666</code></pre>',1,0,17),
(19,'33333333333333','333333333333333','192.168.13.145','内网IP','Windows 10','Chrome 8',18,'222222223',NULL,37,'http://localhost/article/37',NULL,0,0,'333333333333333333333333',NULL,'2019-12-09 17:11:11',NULL,NULL,NULL,NULL,'<p>333333333333333333333333</p>\n',1,0,18),
(20,'22222222222222','22222222222222','192.168.13.145','内网IP','Windows 10','Chrome 8',8,'222222222',NULL,-1000,'http://localhost/article/37',NULL,0,0,'2222222222222222222222222',NULL,'2019-12-09 17:11:27',NULL,NULL,NULL,NULL,'<p>2222222222222222222222222</p>\n',1,0,9),
(21,'321212131','11111','192.168.13.145','内网IP','Windows 10','Chrome 8',8,'12312',NULL,-1000,'http://localhost/article/37',NULL,0,0,'111111111111111111111111',NULL,'2019-12-09 17:19:51',NULL,NULL,NULL,NULL,'<p>111111111111111111111111</p>\n',1,0,20),
(22,'1111111111111111','1111111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',NULL,'1111',NULL,-1000,'http://localhost/article/37',NULL,0,0,'11111111111111111111111',NULL,'2019-12-09 17:19:58',NULL,NULL,NULL,NULL,'<p>11111111111111111111111</p>\n',1,0,NULL),
(23,'1111111111111111','11111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',NULL,'111111111111111111',NULL,23,'http://localhost/article/37',NULL,0,0,'1111111111111111111111111',NULL,'2019-12-09 17:20:12',NULL,NULL,NULL,NULL,'<p>1111111111111111111111111</p>\n',1,0,NULL),
(24,'1111111111111','1111111111111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',23,'11111111111',NULL,23,'http://localhost/article/37',NULL,0,0,'11111111111111111111111',NULL,'2019-12-09 17:20:19',NULL,NULL,NULL,NULL,'<p>11111111111111111111111</p>\n',1,0,23),
(25,'11111111111111','111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',23,'111111111',NULL,23,'http://localhost/article/37',NULL,0,0,'11111111111111111111111111',NULL,'2019-12-09 17:20:25',NULL,NULL,NULL,NULL,'<p>11111111111111111111111111</p>\n',1,0,24),
(26,'11111111111111','1111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',18,'111111111111111111',NULL,37,'http://localhost/',NULL,0,0,'1111111111111111111111111111111111111',NULL,'2019-12-10 14:05:02',NULL,NULL,NULL,NULL,'<p>1111111111111111111111111111111111111</p>\n',1,0,18),
(27,'3232323','23232323','192.168.13.145','内网IP','Windows 10','Chrome 8',26,'3323232',NULL,37,'http://localhost/article/37',NULL,0,0,'32323',NULL,'2019-12-10 14:05:26',NULL,NULL,NULL,NULL,'<p>32323</p>\n',1,0,26),
(28,'11111111111111111','11111111111111','192.168.13.145','内网IP','Windows 10','Chrome 8',26,'1111111111',NULL,37,'http://localhost/article/37',NULL,0,0,'1111111111111111111111111',NULL,'2019-12-10 14:07:45',NULL,NULL,NULL,NULL,'<p>1111111111111111111111111</p>\n',1,0,26);

/*Table structure for table `bg_tag` */

DROP TABLE IF EXISTS `bg_tag`;

CREATE TABLE `bg_tag` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `color` varchar(32) DEFAULT NULL COMMENT '标签轮廓颜色',
                          `title` varchar(128) DEFAULT NULL COMMENT '标签名',
                          `create_by` varchar(128) DEFAULT NULL,
                          `create_time` datetime DEFAULT NULL,
                          `update_by` varchar(128) DEFAULT NULL,
                          `update_time` datetime DEFAULT NULL,
                          `delete_by` varchar(128) DEFAULT NULL,
                          `delete_time` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `bg_tag_title_uindex` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='博客标签表';

/*Data for the table `bg_tag` */

insert  into `bg_tag`(`id`,`color`,`title`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(6,'rgba(229, 52, 214, 0.5)','321311',NULL,'2019-11-28 14:03:52',NULL,NULL,NULL,NULL),
(7,'rgba(183, 181, 146, 1)','整理',NULL,'2019-11-28 14:10:53',NULL,NULL,NULL,NULL),
(8,'rgba(210, 252, 111, 1)','工具集',NULL,'2019-11-28 14:10:53',NULL,NULL,NULL,NULL),
(9,'rgba(237, 75, 212, 1)','程序员',NULL,'2019-11-28 14:10:53',NULL,NULL,NULL,NULL),
(10,'rgba(14, 211, 141, 1)','则提取关键字',NULL,'2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(11,'rgba(16, 159, 108, 1)','ID和tag name定位',NULL,'2019-11-28 14:44:31',NULL,NULL,NULL,NULL),
(12,'rgba(50, 54, 139, 1)','web页面元素',NULL,'2019-11-28 14:44:31',NULL,NULL,NULL,NULL);

/*Table structure for table `bg_visit_log` */

DROP TABLE IF EXISTS `bg_visit_log`;

CREATE TABLE `bg_visit_log` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `ip` varchar(128) DEFAULT NULL COMMENT 'IP地址',
                                `location` varchar(128) DEFAULT NULL COMMENT '地理位置',
                                `browser` varchar(128) DEFAULT NULL COMMENT '浏览器',
                                `os` varchar(128) DEFAULT NULL COMMENT '操作系统',
                                `url` varchar(128) DEFAULT NULL COMMENT '访问URL地址',
                                `error_msg` varchar(2000) DEFAULT NULL,
                                `status` tinyint(4) DEFAULT NULL COMMENT '状态,1表示成功,0表示失败',
                                `title` varchar(128) DEFAULT NULL COMMENT '访问模块',
                                `spider` varchar(128) DEFAULT NULL COMMENT '爬虫',
                                `create_time` datetime DEFAULT NULL,
                                `delete_time` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='访问日志表';

/*Data for the table `bg_visit_log` */

/*Table structure for table `bk_book` */

DROP TABLE IF EXISTS `bk_book`;

CREATE TABLE `bk_book` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `title` varchar(256) DEFAULT NULL COMMENT '标题',
                           `header_img` varchar(256) DEFAULT NULL COMMENT '封面',
                           `author` varchar(128) DEFAULT NULL COMMENT '作者',
                           `category_id` bigint(20) DEFAULT NULL COMMENT '分类Id',
                           `publisher` varchar(128) DEFAULT NULL COMMENT '出版社',
                           `publish_time` datetime DEFAULT NULL COMMENT '出版时间',
                           `page_num` int(11) DEFAULT NULL COMMENT '页数',
                           `grade` decimal(10,0) DEFAULT NULL COMMENT '评分',
                           `description` varchar(2000) DEFAULT NULL COMMENT '简介',
                           `catalog` text COMMENT '原书目录',
                           `click` bigint(20) DEFAULT '0' COMMENT '阅读量',
                           `like` bigint(20) DEFAULT '0',
                           `status` tinyint(4) DEFAULT '1' COMMENT 'true表示发布,false表示草稿箱',
                           `progress` int(11) DEFAULT '0' COMMENT '读书状态',
                           `support` tinyint(4) DEFAULT '0' COMMENT '是否推荐',
                           `create_time` datetime DEFAULT NULL,
                           `create_by` varchar(128) DEFAULT NULL,
                           `update_by` varchar(18) DEFAULT NULL,
                           `update_time` datetime DEFAULT NULL,
                           `delete_by` varchar(128) DEFAULT NULL,
                           `delete_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书';

/*Data for the table `bk_book` */

insert  into `bk_book`(`id`,`title`,`header_img`,`author`,`category_id`,`publisher`,`publish_time`,`page_num`,`grade`,`description`,`catalog`,`click`,`like`,`status`,`progress`,`support`,`create_time`,`create_by`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'111111111111111111','111111111111111111','1111111111111111111111',NULL,'1111111111111','2019-03-08 08:00:00',11,4,'11111111111111111111','11111111111111111',0,0,1,74,1,'2019-12-17 10:28:20','admin',NULL,'2019-12-17 10:45:52','admin','2019-12-17 11:17:51'),
(2,'1111','http://image.banshujiang.cn/1911.jpeg','111111111111111',NULL,'11111111111111111','2019-12-10 08:00:00',11,5,'111111111111111111111111','1111',0,0,1,11,0,'2019-12-17 10:47:54','admin',NULL,'2019-12-17 14:08:54',NULL,NULL),
(3,'深入理解Java虚拟机（第2版）','http://image.banshujiang.cn/1911.jpeg','周志明',NULL,'机械工业出版社','2013-09-01 08:00:00',433,5,'周志明，资深Java技术专家，对JavaEE企业级应用开发、OSGi、Java虚拟机和工作流等都有深入的研究，并在大量的实践中积累了丰富的经验。尤其精通Java虚拟机，撰写了大量与JVM相关的经典文章，被各大技术社区争相转载，是ITeye等技术社区公认的Java虚拟机方面的领袖人物之一。除本书外，还著有经典著作《深入理解OSGi：Equinox原理、应用与最佳实践》，广获读者好评。现任远光软件股份有限公司开发部总经理兼架构师，先后参与过国家电网、南方电网等多个国家级大型ERP项目的平台架构工作，对软件系统架构也有深刻的认识和体会。  《深入理解Java虚拟机:JVM高级特性与最佳实践(第2版)》内容简介：第1版两年内印刷近10次，4家网上书店的评论近4?000条，98%以上的评论全部为5星级的好评，是整个Java图书领域公认的经典著作和超级畅销书，繁体版在台湾也十分受欢迎。第2版在第1版的基础上做了很大的改进：根据最新的JDK 1.7对全书内容进行了全面的升级和补充；增加了大量处理各种常见JVM问题的技巧和最佳实践；增加了若干与生产环境相结合的实战案例；对第1版中的错误和不足之处的修正；等等。第2版不仅技术更新、内容更丰富，而且实战性更强。 《深入理解Java虚拟机:JVM高级特性与最佳实践(第2版)》共分为五大部分，围绕内存管理、执行子系统、程序编译与优化、高效并发等核心主题对JVM进行了全面而深入的分析，深刻揭示了JVM的工作原理。 第一部分从宏观的角度介绍了整个Java技术体系、Java和JVM的发展历程、模块化，以及JDK的编译，这对理解书中后面内容有重要帮助。 第二部分讲解了JVM的自动内存管理，包括虚拟机内存区域的划分原理以及各种内存溢出异常产生的原因；常见的垃圾收集算法以及垃圾收集器的特点和工作原理；常见虚拟机监控与故障处理工具的原理和使用方法。 第三部分分析了虚拟机的执行子系统，包括类文件结构、虚拟机类加载机制、虚拟机字节码执行引擎。 第四部分讲解了程序的编译与代码的优化，阐述了泛型、自动装箱拆箱、条件编译等语法糖的原理；讲解了虚拟机的热点探测方法、HotSpot的即时编译器、编译触发条件，以及如何从虚拟机外部观察和分析JIT编译的数据和结果； 第五部分探讨了Java实现高效并发的原理，包括JVM内存模型的结构和操作；原子性、可见性和有序性在Java内存模型中的体现；先行发生原则的规则和使用；线程在Java语言中的实现原理；虚拟机实现高效并发所做的一系列锁优化措施。','前言\n第一部分走近Java\n第1章走近Java2\n1.1概述2\n1.2Java技术体系3\n1.3Java发展史5\n1.4Java虚拟机发展史9\n1.4.1SunClassicExactVM9\n1.4.2SunHotSpotVM11\n1.4.3SunMobile—EmbeddedVMMeta—CircularVM12\n1.4.4BEAJRockitIBMJ9VM13\n1.4.5AzulVMBEALiquidVM14\n1.4.6ApacheHarmonyGoogleAndroidDalvikVM14\n1.4.7MicrosoftJVM及其他15\n1.5展望Java技术的未来16\n1.5.1模块化17\n1.5.2混合语言17\n1.5.3多核并行19\n1.5.4进一步丰富语法20\n1.5.564位虚拟机21\n1.6实战：自己编译JDK22\n1.6.1获取JDK源码22\n1.6.2系统需求24\n1.6.3构建编译环境25\n1.6.4进行编译26\n1.6.5在IDE工具中进行源码调试31\n1.7本章小结35\n第二部分自动内存管理机制\n第2章Java内存区域与内存溢出异常38\n2.1概述38\n2.2运行时数据区域38\n2.2.1程序计数器39\n2.2.2Java虚拟机栈39\n2.2.3本地方法栈40\n2.2.4Java堆41\n2.2.5方法区41\n2.2.6运行时常量池42\n2.2.7直接内存43\n2.3HotSpot虚拟机对象探秘43\n2.3.1对象的创建44\n2.3.2对象的内存布局47\n2.3.3对象的访问定位48\n2.4实战：OutOfMemoryError异常50\n2.4.1Java堆溢出51\n2.4.2虚拟机栈和本地方法栈溢出53\n2.4.3方法区和运行时常量池溢出56\n2.4.4本机直接内存溢出59\n2.5本章小结60\n第3章垃圾收集器与内存分配策略61\n3.1概述61\n3.2对象已死吗62\n3.2.1引用计数算法62\n3.2.2可达性分析算法64\n3.2.3再谈引用65\n3.2.4生存还是死亡66\n3.2.5回收方法区68\n3.3垃圾收集算法69\n3.3.1标记—清除算法69\n3.3.2复制算法70\n3.3.3标记—整理算法71\n3.3.4分代收集算法72\n3.4HotSpot的算法实现72\n3.4.1枚举根节点72\n3.4.2安全点73\n3.4.3安全区域74\n3.5垃圾收集器75\n3.5.1Serial收集器76\n3.5.2ParNew收集器77\n3.5.3ParallelScavenge收集器79\n3.5.4SerialOld收集器80\n3.5.5ParallelOld收集器80\n3.5.6CMS收集器81\n3.5.7G1收集器84\n3.5.8理解GC日志89\n3.5.9垃圾收集器参数总结90\n3.6内存分配与回收策略91\n3.6.1对象优先在Eden分配91\n3.6.2大对象直接进入老年代93\n3.6.3长期存活的对象将进入老年代95\n3.6.4动态对象年龄判定97\n3.6.5空间分配担保98\n3.7本章小结100\n第4章虚拟机性能监控与故障处理工具101\n4.1概述101\n4.2JDK的命令行工具101\n4.2.1jps：虚拟机进程状况工具104\n4.2.2jstat：虚拟机统计信息监视工具105\n4.2.3jinfo：Java配置信息工具106\n4.2.4jmap：Java内存映像工具107\n4.2.5jhat：虚拟机堆转储快照分析工具108\n4.2.6jstack：Java堆栈跟踪工具109\n4.2.7HSDIS：JIT生成代码反汇编111\n4.3JDK的可视化工具114\n4.3.1JConsole：Java监视与管理控制台115\n4.3.2VisualVM：多合一故障处理工具122\n4.4本章小结131\n第5章调优案例分析与实战132\n5.1概述132\n5.2案例分析132\n5.2.1高性能硬件上的程序部署策略132\n5.2.2集群间同步导致的内存溢出135\n5.2.3堆外内存导致的溢出错误136\n5.2.4外部命令导致系统缓慢137\n5.2.5服务器JVM进程崩溃138\n5.2.6不恰当数据结构导致内存占用过大139\n5.2.7由Windows虚拟内存导致的长时间停顿141\n5.3实战：Eclipse运行速度调优142\n5.3.1调优前的程序运行状态142\n5.3.2升级JDK1.6的性能变化及兼容问题145\n5.3.3编译时间和类加载时间的优化150\n5.3.4调整内存设置控制垃圾收集频率153\n5.3.5选择收集器降低延迟157\n5.4本章小结160\n第三部分虚拟机执行子系统\n第6章类文件结构162\n6.1概述162\n6.2无关性的基石162\n6.3Class类文件的结构164\n6.3.1魔数与Class文件的版本166\n6.3.2常量池167\n6.3.3访问标志173\n6.3.4类索引、父类索引与接口索引集合174\n6.3.5字段表集合175\n6.3.6方法表集合178\n6.3.7属性表集合180\n6.4字节码指令简介196\n6.4.1字节码与数据类型197\n6.4.2加载和存储指令199\n6.4.3运算指令200\n6.4.4类型转换指令202\n6.4.5对象创建与访问指令203\n6.4.6操作数栈管理指令203\n6.4.7控制转移指令204\n6.4.8方法调用和返回指令204\n6.4.9异常处理指令205\n6.4.10同步指令205\n6.5公有设计和私有实现206\n6.6Class文件结构的发展207\n6.7本章小结208\n第7章虚拟机类加载机制209\n7.1概述209\n7.2类加载的时机210\n7.3类加载的过程214\n7.3.1加载214\n7.3.2验证216\n7.3.3准备219\n7.3.4解析220\n7.3.5初始化225\n7.4类加载器227\n7.4.1类与类加载器228\n7.4.2双亲委派模型229\n7.4.3破坏双亲委派模型233\n7.5本章小结235\n第8章虚拟机字节码执行引擎236\n8.1概述236\n8.2运行时栈帧结构236\n8.2.1局部变量表238\n8.2.2操作数栈242\n8.2.3动态连接243\n8.2.4方法返回地址243\n8.2.5附加信息244\n8.3方法调用244\n8.3.1解析244\n8.3.2分派246\n8.3.3动态类型语言支持258\n8.4基于栈的字节码解释执行引擎269\n8.4.1解释执行269\n8.4.2基于栈的指令集与基于寄存器的指令集270\n8.4.3基于栈的解释器执行过程272\n8.5本章小结275\n第9章类加载及执行子系统的案例与实战276\n9.1概述276\n9.2案例分析276\n9.2.1Tomcat：正统的类加载器架构276\n9.2.2OSGi：灵活的类加载器架构279\n9.2.3字节码生成技术与动态代理的实现282\n9.2.4Retrotranslator：跨越JDK版本286\n9.3实战：自己动手实现远程执行功能289\n9.3.1目标290\n9.3.2思路290\n9.3.3实现291\n9.3.4验证298\n9.4本章小结299\n第四部分程序编译与代码优化\n第10章早期（编译期）优化302\n10.1概述302\n10.2Javac编译器303\n10.2.1Javac的源码与调试303\n10.2.2解析与填充符号表305\n10.2.3注解处理器307\n10.2.4语义分析与字节码生成307\n10.3Java语法糖的味道311\n10.3.1泛型与类型擦除311\n10.3.2自动装箱、拆箱与遍历循环315\n10.3.3条件编译317\n10.4实战：插入式注解处理器318\n10.4.1实战目标318\n10.4.2代码实现319\n10.4.3运行与测试326\n10.4.4其他应用案例327\n10.5本章小结328\n第11章晚期（运行期）优化329\n11.1概述329\n11.2HotSpot虚拟机内的即时编译器329\n11.2.1解释器与编译器330\n11.2.2编译对象与触发条件332\n11.2.3编译过程337\n11.2.4查看及分析即时编译结果339\n11.3编译优化技术345\n11.3.1优化技术概览346\n11.3.2公共子表达式消除350\n11.3.3数组边界检查消除351\n11.3.4方法内联352\n11.3.5逃逸分析354\n11.4Java与C/C++的编译器对比356\n11.5本章小结358\n第五部分高效并发\n第12章Java内存模型与线程360\n12.1概述360\n12.2硬件的效率与一致性361\n12.3Java内存模型362\n12.3.1主内存与工作内存363\n12.3.2内存间交互操作364\n12.3.3对于volatile型变量的特殊规则366\n12.3.4对于long和double型变量的特殊规则372\n12.3.5原子性、可见性与有序性373\n12.3.6先行发生原则375\n12.4Java与线程378\n12.4.1线程的实现378\n12.4.2Java线程调度381\n12.4.3状态转换383\n12.5本章小结384\n第13章线程安全与锁优化385\n13.1概述385\n13.2线程安全385\n13.2.1Java语言中的线程安全386\n13.2.2线程安全的实现方法390\n13.3锁优化397\n13.3.1自旋锁与自适应自旋398\n13.3.2锁消除398\n13.3.3锁粗化400\n13.3.4轻量级锁400\n13.3.5偏向锁402\n13.4本章小结403\n附录\n附录A编译Windows版的OpenJDK406\n附录B虚拟机字节码指令表414\n附录CHotSpot虚拟机主要参数表420\n附录D对象查询语言（OQL）简介424\n附录EJDK历史版本轨迹430',0,0,0,29,0,'2019-12-17 14:09:54','admin',NULL,NULL,NULL,NULL);

/*Table structure for table `bk_category` */

DROP TABLE IF EXISTS `bk_category`;

CREATE TABLE `bk_category` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `title` varchar(128) DEFAULT NULL COMMENT '分类名称',
                               `description` varchar(512) DEFAULT NULL COMMENT '描述',
                               `create_by` varchar(128) DEFAULT NULL,
                               `create_time` datetime DEFAULT NULL,
                               `update_by` varchar(128) DEFAULT NULL,
                               `update_time` datetime DEFAULT NULL,
                               `delete_by` varchar(128) DEFAULT NULL,
                               `delete_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书分类表';

/*Data for the table `bk_category` */

insert  into `bk_category`(`id`,`title`,`description`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'111111111111','11111111111111111111','admin','2019-12-17 09:56:48',NULL,NULL,'admin','2019-12-17 09:56:51'),
(2,'11111111111','111111111111111111111111','admin','2019-12-17 10:21:46',NULL,NULL,NULL,NULL);

/*Table structure for table `bk_note` */

DROP TABLE IF EXISTS `bk_note`;

CREATE TABLE `bk_note` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `title` varchar(256) DEFAULT NULL COMMENT '标题',
                           `summary` varchar(512) DEFAULT NULL COMMENT '描述',
                           `content` longtext COMMENT '内容',
                           `html_content` longtext COMMENT 'HTML 格式化后的内容',
                           `click` bigint(20) DEFAULT '0' COMMENT '点击量',
                           `like` bigint(20) DEFAULT '0' COMMENT '点赞',
                           `chapter` varchar(256) DEFAULT NULL COMMENT '所属章节',
                           `book_id` bigint(20) DEFAULT NULL COMMENT '图书Id',
                           `header_img` varchar(256) DEFAULT NULL COMMENT '封面',
                           `support` tinyint(4) DEFAULT NULL COMMENT '推荐',
                           `comment` tinyint(4) DEFAULT NULL COMMENT '是否允许评论',
                           `status` tinyint(4) DEFAULT NULL COMMENT '笔记状态(1表示发表,0表示草稿箱)',
                           `weight` bigint(20) DEFAULT NULL COMMENT '权重',
                           `create_by` varchar(128) DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `update_by` varchar(128) DEFAULT NULL,
                           `update_time` varchar(128) DEFAULT NULL,
                           `delete_by` varchar(128) DEFAULT NULL,
                           `delete_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='读书笔记表';

/*Data for the table `bk_note` */

/*Table structure for table `bk_note_tag` */

DROP TABLE IF EXISTS `bk_note_tag`;

CREATE TABLE `bk_note_tag` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `note_id` bigint(20) DEFAULT NULL,
                               `tag_id` bigint(20) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='note和tag的关联表';

/*Data for the table `bk_note_tag` */

/*Table structure for table `bk_tag` */

DROP TABLE IF EXISTS `bk_tag`;

CREATE TABLE `bk_tag` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `color` varchar(128) DEFAULT NULL,
                          `title` varchar(128) DEFAULT NULL,
                          `create_by` varchar(128) DEFAULT NULL,
                          `create_time` datetime DEFAULT NULL,
                          `update_by` varchar(128) DEFAULT NULL,
                          `update_time` datetime DEFAULT NULL,
                          `delete_by` varchar(128) DEFAULT NULL,
                          `delete_time` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='book的tag';

/*Data for the table `bk_tag` */

insert  into `bk_tag`(`id`,`color`,`title`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'rgba(173, 57, 57, 1)','1111111111','admin','2019-12-17 13:52:53',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_blacklist` */

DROP TABLE IF EXISTS `sys_blacklist`;

CREATE TABLE `sys_blacklist` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `ip` varchar(128) DEFAULT NULL COMMENT 'ip地址',
                                 `description` varchar(256) DEFAULT NULL COMMENT '封禁描述',
                                 `intercept_count` bigint(20) DEFAULT NULL COMMENT '封禁后再访问次数',
                                 `last_access_url` varchar(256) DEFAULT NULL COMMENT '上次访问的url',
                                 `last_access_time` datetime DEFAULT NULL COMMENT '上次访问时间',
                                 `create_by` varchar(150) DEFAULT NULL,
                                 `create_time` datetime DEFAULT NULL,
                                 `update_by` varchar(150) DEFAULT NULL,
                                 `update_time` datetime DEFAULT NULL,
                                 `delete_by` varchar(150) DEFAULT NULL,
                                 `delete_time` datetime DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统黑名单';

/*Data for the table `sys_blacklist` */

insert  into `sys_blacklist`(`id`,`ip`,`description`,`intercept_count`,`last_access_url`,`last_access_time`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'11321','1',NULL,NULL,NULL,'admin','2019-10-24 18:39:37',NULL,NULL,'admin','2019-10-28 17:12:02'),
(2,'45640','232',NULL,NULL,NULL,'admin','2019-10-24 18:44:05',NULL,NULL,'admin','2019-10-28 17:12:06'),
(3,'45640','232',NULL,NULL,NULL,'admin','2019-10-24 18:44:05',NULL,NULL,'admin','2019-10-28 17:12:09'),
(4,'121321','121312',NULL,NULL,NULL,'admin','2019-10-28 17:12:14',NULL,NULL,'admin','2019-10-28 17:12:16'),
(5,'11111111111','11111111111',NULL,NULL,NULL,'admin','2019-11-06 12:03:56',NULL,NULL,'admin','2019-11-06 12:09:29'),
(6,'1111111','11111111111111',NULL,NULL,NULL,'admin','2019-11-06 12:11:37',NULL,NULL,'admin','2019-11-06 12:12:23'),
(7,'1111111111','1111111111111',NULL,NULL,NULL,'admin','2019-11-06 12:13:50',NULL,NULL,'admin','2019-11-06 12:13:53'),
(8,'111111','1111111',NULL,NULL,NULL,'admin','2019-11-06 12:13:58',NULL,NULL,'admin','2019-11-06 12:14:00'),
(9,'1111111111','111111111111',NULL,NULL,NULL,'admin','2019-11-06 12:14:33',NULL,NULL,'admin','2019-11-06 12:14:37'),
(10,'127.22.32.3','1231',NULL,NULL,NULL,'admin','2019-11-08 18:59:19',NULL,NULL,'admin','2019-11-08 19:00:23'),
(11,'195.23.23.22','1513',NULL,NULL,NULL,'admin','2019-11-08 19:00:16',NULL,NULL,'admin','2019-11-08 19:00:42'),
(12,'12.0.0.1','111111111111111111',NULL,NULL,NULL,'admin','2019-11-12 17:59:23',NULL,NULL,'admin','2019-11-12 17:59:28'),
(13,'127.0.0.1','123231',NULL,NULL,NULL,'admin','2019-11-12 17:59:35',NULL,NULL,'admin','2019-11-12 17:59:43');

/*Table structure for table `sys_carousel` */

DROP TABLE IF EXISTS `sys_carousel`;

CREATE TABLE `sys_carousel` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `description` varchar(512) DEFAULT NULL COMMENT '显示文本',
                                `click` bigint(20) DEFAULT NULL COMMENT '点击次数',
                                `header_img` varchar(256) DEFAULT NULL COMMENT '图片URL',
                                `display` tinyint(4) DEFAULT NULL COMMENT '是否显示',
                                `target` tinyint(4) DEFAULT NULL COMMENT '是否当前窗口打开',
                                `url` varchar(128) DEFAULT NULL COMMENT '链接地址',
                                `title` varchar(128) DEFAULT NULL COMMENT 'header',
                                `create_by` varchar(128) DEFAULT NULL,
                                `create_time` datetime DEFAULT NULL,
                                `update_by` varchar(128) DEFAULT NULL,
                                `delete_by` varchar(128) DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                `delete_time` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='轮播图';

/*Data for the table `sys_carousel` */

insert  into `sys_carousel`(`id`,`description`,`click`,`header_img`,`display`,`target`,`url`,`title`,`create_by`,`create_time`,`update_by`,`delete_by`,`update_time`,`delete_time`) values
(1,'111111111111111',NULL,'https://s2.ax1x.com/2019/07/31/etHSHS.jpg',1,1,NULL,'《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）24','admin',NULL,NULL,'admin',NULL,NULL),
(2,'不负时代 沉睡的人心潮澎湃 热血流过动脉 别让梦想带着遗憾醒来 总有人被世界淘汰 总有人被记载 在这尔虞我诈的舞台挺过来 初心仍在 坎坷不算失败 不负时代 人生没有彩排 Oh 起落... 查看更多 ',NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',1,1,NULL,'《吐血整理》-顶级程序员工具集23',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
                              `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
                              `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
                              `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
                              `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                              `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `delete_by` varchar(64) DEFAULT '',
                              `delete_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`config_key`,`config_value`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'sys.index.skinName','skin-blue','蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL),
(2,'sys.user.initPassword','123456','初始化密码 123456','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL),
(3,'sys.index.sideTheme','theme-dark','深色主题theme-dark，浅色主题theme-light','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','admin','2019-11-06 14:39:11'),
(4,'12121','12121',NULL,'admin','2019-10-23 14:41:55','',NULL,'admin','2019-10-23 14:43:06'),
(5,'qiNiuConfig','{\"accessKey\":\"adminBRR72W1AwFtqbAKVkpNIQl0vNzwqwqwq2UIhNsWT6MjpMG\",\"bucket\":\"dimpleblog\",\"host\":\" q0jwwt1l6.bkt.clouddn.com\",\"secretKey\":\"qtiuY6dS7bKEMBTqwqwq_nhBnJYC_Bytos07TQ_dDXjM4\",\"type\":\"公开\",\"zone\":\"华南\"}',NULL,'','2019-11-07 16:12:00','',NULL,'',NULL);

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
                                 `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                 `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
                                 `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
                                 `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                 `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
                                 `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                                 `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,1,'男','0','sys_user_sex','','','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'性别男'),
(2,2,'女','1','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'性别女'),
(3,3,'未知','2','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'性别未知'),
(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'显示菜单'),
(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'隐藏菜单'),
(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'正常状态'),
(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'停用状态'),
(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'正常状态'),
(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'停用状态'),
(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'默认分组'),
(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'系统分组'),
(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'系统默认是'),
(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'系统默认否'),
(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'通知'),
(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'公告'),
(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'正常状态'),
(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'关闭状态'),
(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'新增操作'),
(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'修改操作'),
(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'删除操作'),
(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'授权操作'),
(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'导出操作'),
(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'导入操作'),
(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'强退操作'),
(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'生成操作'),
(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'清空操作'),
(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'正常状态'),
(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'停用状态'),
(29,1,'草稿','false','bg_blog_status',NULL,NULL,'N','0','admin','2019-10-31 14:33:38','admin','2019-10-31 14:35:03','',NULL,'草稿箱'),
(30,2,'发布','true','bg_blog_status',NULL,NULL,'N','0','admin','2019-10-31 14:34:45','admin','2019-10-31 14:34:53','',NULL,'已发布'),
(31,1,'是','true','bg_blog_support',NULL,NULL,'N','0','admin','2019-10-31 14:46:49','',NULL,'',NULL,'推荐'),
(32,2,'否','false','bg_blog_support',NULL,NULL,'N','0','admin','2019-10-31 14:47:22','',NULL,'',NULL,'不推荐'),
(33,1,'开启','true','bg_blog_comment',NULL,NULL,'N','0','admin','2019-10-31 18:38:35','',NULL,'',NULL,'开启'),
(34,2,'关闭','false','bg_blog_comment',NULL,NULL,'N','0','admin','2019-10-31 18:38:53','',NULL,'',NULL,'关闭');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                 `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                                 `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'用户性别','sys_user_sex','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'用户性别列表'),
(2,'菜单状态','sys_show_hide','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'菜单状态列表'),
(3,'系统开关','sys_normal_disable','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'系统开关列表'),
(4,'任务状态','sys_job_status','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'任务状态列表'),
(5,'任务分组','sys_job_group','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'任务分组列表'),
(6,'系统是否','sys_yes_no','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'系统是否列表'),
(7,'通知类型','sys_notice_type','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'通知类型列表'),
(8,'通知状态','sys_notice_status','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'通知状态列表'),
(9,'操作类型','sys_oper_type','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'操作类型列表'),
(10,'系统状态','sys_common_status','0','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'登录状态列表'),
(11,'博客状态','bg_blog_status','0','admin','2019-10-31 14:15:39','',NULL,'',NULL,'博客状态列表'),
(12,'博客推荐','bg_blog_support','0','admin','2019-10-31 14:46:33','',NULL,'',NULL,'博客推荐列表'),
(13,'博客评论','bg_blog_comment','0','admin','2019-10-31 18:38:12','',NULL,'',NULL,'博客评论列表');

/*Table structure for table `sys_link` */

DROP TABLE IF EXISTS `sys_link`;

CREATE TABLE `sys_link` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `title` varchar(128) DEFAULT NULL COMMENT '友链名称',
                            `url` varchar(128) DEFAULT NULL COMMENT '友链地址',
                            `description` varchar(512) DEFAULT NULL COMMENT '友链描述',
                            `header_img` varchar(128) DEFAULT NULL COMMENT '网站图片',
                            `status` tinyint(4) DEFAULT '0' COMMENT '1表示审核通过,0表示未审核',
                            `display` tinyint(4) DEFAULT '0' COMMENT '是否显示友链',
                            `email` varchar(128) DEFAULT NULL COMMENT '站长邮箱地址',
                            `weight` bigint(20) DEFAULT '0' COMMENT '权重',
                            `create_by` varchar(128) DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `update_by` varchar(128) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `delete_by` varchar(128) DEFAULT NULL,
                            `delete_time` datetime DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='友链表';

/*Data for the table `sys_link` */

insert  into `sys_link`(`id`,`title`,`url`,`description`,`header_img`,`status`,`display`,`email`,`weight`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'1232222222222222','https://bianxiaofeng.com/front/images/ava.png','ewew','https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar',1,1,'21@email.com',122,'admin','2019-10-29 12:21:10','admin','2019-10-30 15:59:28',NULL,NULL),
(2,'背景图片','http://www.bianxiaofeng.com','1111','https://bianxiaofeng.com/front/images/ava.png',1,1,'biianxiaofeng@sohu.com',11,'admin','2019-11-08 22:40:25','admin','2019-11-12 18:20:25',NULL,NULL),
(3,'1232222222222222','https://bianxiaofeng.com/front/images/ava.png','ewew','https://bianxiaofeng.com/front/images/ava.png',1,1,'21@email.com',122,'admin','2019-10-29 12:21:10','admin','2019-10-30 15:59:28',NULL,NULL),
(4,'背景图片模糊，其中文字清晰实现方法总结','http://www.bianxiaofeng.com','type 布局模式，可选值为flex流式布局或不选，在现代浏览器下有效图片的盒子imgbox，设置的padding-bottom，当该值为百分比属性时，是基于父元素宽度的百分比，所以这里设置100%的时候，就相当于形成了一个正方形。\r\n','https://ae01.alicdn.com/kf/HTB1MjqzainrK1Rjy1Xcq6yeDVXa9.jpg',1,1,'biianxiaofeng@sohu.com',11,'admin','2019-11-08 22:40:25','admin','2019-11-12 18:20:25',NULL,NULL),
(5,'https://www.bianxiaofeng.com/','https://www.bianxiaofeng.com/','https://www.bianxiaofeng.com/','https://www.bianxiaofeng.com/',0,0,'https://www.bianxiaofeng.com/',0,NULL,'2019-12-05 12:43:52',NULL,NULL,NULL,NULL),
(6,'https://www.bianxiaofeng.com/','https://www.bianxiaofeng.com/','https://www.bianxiaofeng.com/','https://www.bianxiaofeng.com/',0,0,'wss@ss.com',0,NULL,'2019-12-05 12:44:27',NULL,NULL,NULL,NULL),
(7,'www.bianxiaofeng.com','www.bianxiaofeng.com','www.bianxiaofeng.com','www.bianxiaofeng.com',0,0,'www.bianxiaofeng.com',0,NULL,'2019-12-05 15:23:31',NULL,NULL,NULL,NULL),
(8,'11','1','111','111',0,0,'11',0,NULL,'2019-12-05 15:23:36',NULL,NULL,NULL,NULL),
(9,'11','111111','1111111','1111',0,0,'111111',0,NULL,'2019-12-05 15:27:42',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
                                 `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
                                 `ip` varchar(50) DEFAULT '' COMMENT '登录IP地址',
                                 `location` varchar(255) DEFAULT '' COMMENT '登录地点',
                                 `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
                                 `os` varchar(50) DEFAULT '' COMMENT '操作系统',
                                 `status` tinyint(4) DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
                                 `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
                                 `create_time` datetime DEFAULT NULL COMMENT '访问时间',
                                 `create_by` varchar(128) DEFAULT NULL,
                                 `delete_by` varchar(128) DEFAULT NULL,
                                 `delete_time` datetime DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `sys_login_log` */

insert  into `sys_login_log`(`id`,`user_name`,`ip`,`location`,`browser`,`os`,`status`,`msg`,`create_time`,`create_by`,`delete_by`,`delete_time`) values
(26,'admin','192.168.20.56','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-11-22 17:29:29',NULL,'admin','2019-12-12 16:26:15'),
(27,'admin','192.168.20.56','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-11-22 18:12:20',NULL,'admin','2019-12-12 16:26:15'),
(28,'admin','192.168.20.56','内网IP','Firefox 7','Windows 10',1,'登录成功','2019-11-26 16:32:42',NULL,'admin','2019-12-12 16:26:15'),
(29,'admin','192.168.20.56','内网IP','Firefox 7','Windows 10',1,'登录成功','2019-11-26 17:13:33',NULL,'admin','2019-12-12 16:26:15'),
(30,'admin','192.168.20.56','内网IP','Firefox 7','Windows 10',1,'登录成功','2019-11-26 18:34:13',NULL,'admin','2019-12-12 16:26:15'),
(31,'admin','192.168.20.56','内网IP','Firefox 7','Windows 10',1,'登录成功','2019-11-28 13:49:39',NULL,'admin','2019-12-12 16:26:15'),
(32,'admin','222.209.155.52','四川 成都','Chrome 8','Windows 10',1,'登录成功','2019-11-28 22:25:10',NULL,'admin','2019-12-12 16:26:15'),
(33,'admin','222.209.155.52','四川 成都','Chrome 8','Windows 10',1,'退出成功','2019-11-28 22:26:43',NULL,'admin','2019-12-12 16:26:15'),
(34,'admin','222.209.155.52','四川 成都','Chrome 8','Windows 10',1,'登录成功','2019-11-28 22:26:52',NULL,'admin','2019-12-12 16:26:15'),
(35,'admin','222.209.155.52','四川 成都','Mobile Safari','Mac OS X (iPhone)',0,'用户不存在/密码错误','2019-11-28 22:29:03',NULL,'admin','2019-12-12 16:26:15'),
(36,'admin','222.209.155.52','四川 成都','Mobile Safari','Mac OS X (iPhone)',1,'登录成功','2019-11-28 22:29:16',NULL,'admin','2019-12-12 16:26:15'),
(37,'admin','222.209.155.52','四川 成都','Mobile Safari','Mac OS X (iPhone)',1,'退出成功','2019-11-28 22:29:41',NULL,'admin','2019-12-12 16:26:15'),
(38,'admin','125.69.76.42','四川 成都','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-11-29 10:54:37',NULL,'admin','2019-12-12 16:26:15'),
(39,'admin','125.69.76.42','四川 成都','Chrome 8','Windows 10',1,'登录成功','2019-11-29 10:54:46',NULL,'admin','2019-12-12 16:26:15'),
(40,'admin','125.69.76.42','四川 成都','Chrome 8','Windows 10',1,'退出成功','2019-11-29 10:57:14',NULL,'admin','2019-12-12 16:26:15'),
(41,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:06:51',NULL,'admin','2019-12-12 16:26:15'),
(42,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:07:01',NULL,'admin','2019-12-12 16:26:15'),
(43,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'验证码已失效','2019-12-11 14:07:09',NULL,'admin','2019-12-12 16:26:15'),
(44,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:07:15',NULL,'admin','2019-12-12 16:26:15'),
(45,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'验证码错误','2019-12-11 14:09:36',NULL,'admin','2019-12-12 16:26:15'),
(46,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:09:42',NULL,'admin','2019-12-12 16:26:15'),
(47,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'验证码已失效','2019-12-11 14:09:53',NULL,'admin','2019-12-12 16:26:15'),
(48,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:09:59',NULL,'admin','2019-12-12 16:26:15'),
(49,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'验证码已失效','2019-12-11 14:10:54',NULL,'admin','2019-12-12 16:26:15'),
(50,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:10:58',NULL,'admin','2019-12-12 16:26:15'),
(51,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'用户不存在/密码错误','2019-12-11 14:11:06',NULL,'admin','2019-12-12 16:26:15'),
(52,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-11 14:13:00',NULL,'admin','2019-12-12 16:26:15'),
(53,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-11 15:38:41',NULL,'admin','2019-12-12 16:26:15'),
(54,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'验证码已失效','2019-12-11 16:59:56',NULL,'admin','2019-12-12 16:26:15'),
(55,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',0,'验证码已失效','2019-12-11 17:00:00',NULL,'admin','2019-12-12 16:26:15'),
(56,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-11 17:00:05',NULL,'admin','2019-12-12 16:26:15'),
(57,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-12 09:40:28',NULL,'admin','2019-12-12 16:26:15'),
(58,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-12 13:38:11',NULL,'admin','2019-12-12 16:26:15'),
(59,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-12 16:21:43',NULL,'admin','2019-12-12 16:26:15'),
(60,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-16 16:50:09',NULL,NULL,NULL),
(61,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 09:46:05',NULL,NULL,NULL),
(62,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 12:23:08',NULL,NULL,NULL),
(63,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 13:37:00',NULL,NULL,NULL),
(64,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'退出成功','2019-12-17 14:00:10',NULL,NULL,NULL),
(65,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 14:00:16',NULL,NULL,NULL),
(66,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'退出成功','2019-12-17 14:02:58',NULL,NULL,NULL),
(67,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 14:03:11',NULL,NULL,NULL),
(68,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'退出成功','2019-12-17 15:11:02',NULL,NULL,NULL),
(69,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 15:11:08',NULL,NULL,NULL),
(70,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 15:15:07',NULL,NULL,NULL),
(71,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 15:20:45',NULL,NULL,NULL),
(72,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'退出成功','2019-12-17 15:31:00',NULL,NULL,NULL),
(73,'admin','192.168.13.145','内网IP','Chrome 8','Windows 10',1,'登录成功','2019-12-17 15:31:05',NULL,NULL,NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                            `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
                            `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
                            `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
                            `path` varchar(200) DEFAULT '' COMMENT '路由地址',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
                            `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
                            `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（0目录 1菜单 2按钮）',
                            `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                            `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
                            `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                            `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                            `remark` varchar(500) DEFAULT '' COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1108 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'系统管理',0,5,'system',NULL,1,'M','0','','system','admin','2018-03-16 11:33:00','admin','2019-10-24 10:33:09','',NULL,'系统管理目录'),
(2,'系统监控',0,4,'monitor',NULL,1,'M','0','','monitor','admin','2018-03-16 11:33:00','admin','2019-10-24 10:33:16','',NULL,'系统监控目录'),
(100,'用户管理',1,1,'user','system/user/index',1,'C','0','system:user:list','user','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'用户管理菜单'),
(101,'角色管理',1,2,'role','system/role/index',1,'C','0','system:role:list','peoples','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'角色管理菜单'),
(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','system:menu:list','tree-table','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'菜单管理菜单'),
(105,'字典管理',1,6,'dict','system/dict/index',1,'C','0','system:dict:list','dict','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'字典管理菜单'),
(106,'参数设置',1,7,'config','system/config/index',1,'C','0','system:config:list','edit','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'参数设置菜单'),
(107,'通知公告',1,8,'notice','system/notice/index',1,'C','0','system:notice:list','message','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'通知公告菜单'),
(108,'日志管理',0,1,'log','',1,'M','0','','log','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'日志管理菜单'),
(109,'在线用户',2,1,'online','monitor/online/index',1,'C','0','monitor:online:list','online','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'在线用户菜单'),
(110,'定时任务',1095,2,'tool/quartz','tool/quartz/index',1,'C','0','monitor:job:list','job','admin','2018-03-16 11:33:00','admin','2019-11-07 21:43:20','',NULL,'定时任务菜单'),
(111,'数据监控',2,3,'druid','monitor/druid/index',1,'C','0','monitor:druid:list','druid','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'数据监控菜单'),
(112,'服务监控',2,4,'server','monitor/server/index',1,'C','0','monitor:server:list','server','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'服务监控菜单'),
(115,'系统接口',2,3,'swagger','monitor/swagger/index',1,'C','0','tool:swagger:list','swagger','admin','2018-03-16 11:33:00','admin','2019-10-24 10:21:02','',NULL,'系统接口菜单'),
(500,'操作日志',108,1,'operateLog','log/operateLog/index',1,'C','0','monitor:operlog:list','form','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'操作日志菜单'),
(501,'登录日志',108,2,'loginLog','log/loginLog/index',1,'C','0','monitor:logininfor:list','logininfor','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,'登录日志菜单'),
(1001,'用户查询',100,1,'','',1,'F','0','system:user:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1002,'用户新增',100,2,'','',1,'F','0','system:user:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1003,'用户修改',100,3,'','',1,'F','0','system:user:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1004,'用户删除',100,4,'','',1,'F','0','system:user:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1005,'用户导出',100,5,'','',1,'F','0','system:user:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1006,'用户导入',100,6,'','',1,'F','0','system:user:import','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1007,'重置密码',100,7,'','',1,'F','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1008,'角色查询',101,1,'','',1,'F','0','system:role:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1009,'角色新增',101,2,'','',1,'F','0','system:role:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1010,'角色修改',101,3,'','',1,'F','0','system:role:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1011,'角色删除',101,4,'','',1,'F','0','system:role:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1012,'角色导出',101,5,'','',1,'F','0','system:role:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1013,'菜单查询',102,1,'','',1,'F','0','system:menu:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1014,'菜单新增',102,2,'','',1,'F','0','system:menu:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1015,'菜单修改',102,3,'','',1,'F','0','system:menu:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1016,'菜单删除',102,4,'','',1,'F','0','system:menu:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1026,'字典查询',105,1,'#','',1,'F','0','system:dict:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1027,'字典新增',105,2,'#','',1,'F','0','system:dict:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1028,'字典修改',105,3,'#','',1,'F','0','system:dict:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1029,'字典删除',105,4,'#','',1,'F','0','system:dict:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1030,'字典导出',105,5,'#','',1,'F','0','system:dict:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1031,'参数查询',106,1,'#','',1,'F','0','system:config:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1032,'参数新增',106,2,'#','',1,'F','0','system:config:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1033,'参数修改',106,3,'#','',1,'F','0','system:config:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1034,'参数删除',106,4,'#','',1,'F','0','system:config:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1035,'参数导出',106,5,'#','',1,'F','0','system:config:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1036,'公告查询',107,1,'#','',1,'F','0','system:notice:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1037,'公告新增',107,2,'#','',1,'F','0','system:notice:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1038,'公告修改',107,3,'#','',1,'F','0','system:notice:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1039,'公告删除',107,4,'#','',1,'F','0','system:notice:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1040,'操作查询',500,1,'#','',1,'F','0','monitor:operlog:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1041,'操作删除',500,2,'#','',1,'F','0','monitor:operlog:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1042,'日志导出',500,4,'#','',1,'F','0','monitor:operlog:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1043,'登录查询',501,1,'#','',1,'F','0','monitor:logininfor:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1044,'登录删除',501,2,'#','',1,'F','0','monitor:logininfor:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1045,'日志导出',501,3,'#','',1,'F','0','monitor:logininfor:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1046,'在线查询',109,1,'#','',1,'F','0','monitor:online:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1047,'批量强退',109,2,'#','',1,'F','0','monitor:online:batchLogout','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1048,'单条强退',109,3,'#','',1,'F','0','monitor:online:forceLogout','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1049,'任务查询',110,1,'#','',1,'F','0','monitor:job:query','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1050,'任务新增',110,2,'#','',1,'F','0','monitor:job:add','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1051,'任务修改',110,3,'#','',1,'F','0','monitor:job:edit','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1052,'任务删除',110,4,'#','',1,'F','0','monitor:job:remove','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1053,'状态修改',110,5,'#','',1,'F','0','monitor:job:changeStatus','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1054,'任务导出',110,7,'#','',1,'F','0','monitor:job:export','#','admin','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','',NULL,''),
(1066,'博客管理',0,1,'',NULL,1,'M','0','','nested','admin','2019-10-24 10:25:45','admin','2019-11-06 11:05:47','',NULL,''),
(1067,'博文管理',1066,1,'/blog/blog','blog/blog/index',1,'C','0','blog:blog:list','dict','admin','2019-10-24 10:26:41','admin','2019-10-24 14:39:17','',NULL,''),
(1068,'评论管理',1066,4,'/blog/comment','blog/comment/index',1,'C','0','blog:comment:list','component','admin','2019-10-24 10:31:20','',NULL,'',NULL,''),
(1069,'类别管理',1066,2,'/blog/category','blog/category/index',1,'C','0','blog:category:list','download','admin','2019-10-24 10:32:17','',NULL,'',NULL,''),
(1071,'黑名单',2,6,'/monitor/blacklist','monitor/blacklist/index',1,'C','0','monitor:blacklist:list','drag','admin','2019-10-24 16:49:08','',NULL,'',NULL,''),
(1072,'友链管理',1,6,'/system/link','system/link/index',1,'C','0','system:link:list','404','admin','2019-10-29 11:27:42','',NULL,'',NULL,''),
(1073,'博文查询',1067,1,'',NULL,1,'F','0','blog:blog:query','#','admin','2019-11-01 17:25:38','',NULL,'',NULL,''),
(1074,'博文修改',1067,2,'',NULL,1,'F','0','blog:blog:edit','#','admin','2019-11-01 17:25:56','',NULL,'',NULL,''),
(1075,'博文新增',1067,3,'',NULL,1,'F','0','blog:blog:add','#','admin','2019-11-01 17:26:14','',NULL,'',NULL,''),
(1076,'博文删除',1067,4,'',NULL,1,'F','0','blog:blog:remove','#','admin','2019-11-01 17:26:39','',NULL,'',NULL,''),
(1077,'类别新增',1069,1,'',NULL,1,'F','0','blog:category:add','#','admin','2019-11-01 17:27:09','',NULL,'',NULL,''),
(1078,'类别删除',1069,2,'',NULL,1,'F','0','blog:category:remove','#','admin','2019-11-01 17:27:27','',NULL,'',NULL,''),
(1079,'分类查询',1069,3,'',NULL,1,'F','0','blog:category:query','#','admin','2019-11-01 17:27:49','',NULL,'',NULL,''),
(1080,'分类修改',1069,4,'',NULL,1,'F','0','blog:category:edit','#','admin','2019-11-01 17:28:09','',NULL,'',NULL,''),
(1081,'评论新增',1068,1,'',NULL,1,'F','0','blog:comment:add','#','admin','2019-11-01 17:28:28','',NULL,'',NULL,''),
(1082,'评论删除',1068,2,'',NULL,1,'F','0','blog:comment:remove','#','admin','2019-11-01 17:28:50','',NULL,'',NULL,''),
(1083,'评论修改',1068,3,'',NULL,1,'F','0','blog:comment:edit','#','admin','2019-11-01 17:29:32','',NULL,'',NULL,''),
(1084,'评论查询',1068,4,'',NULL,1,'F','0','blog:comment:query','#','admin','2019-11-01 17:29:51','',NULL,'',NULL,''),
(1085,'友链新增',1072,1,'',NULL,1,'F','0','system:link:add','#','admin','2019-11-01 17:30:17','',NULL,'',NULL,''),
(1086,'友链删除',1072,2,'',NULL,1,'F','0','system:link:remove','#','admin','2019-11-01 17:30:34','',NULL,'',NULL,''),
(1087,'友链查询',1072,3,'',NULL,1,'F','0','system:link:query','#','admin','2019-11-01 17:30:52','',NULL,'',NULL,''),
(1088,'友链修改',1072,4,'',NULL,1,'F','0','system:link:edit','#','admin','2019-11-01 17:31:08','',NULL,'',NULL,''),
(1089,'黑名单新增',1071,1,'',NULL,1,'F','0','monitor:blacklist:add','#','admin','2019-11-01 17:31:37','',NULL,'',NULL,''),
(1090,'黑名单修改',1071,2,'',NULL,1,'F','0','monitor:blacklist:edit','#','admin','2019-11-01 17:31:53','',NULL,'',NULL,''),
(1091,'黑名单删除',1071,3,'',NULL,1,'F','0','monitor:blacklist:remove','#','admin','2019-11-01 17:32:10','',NULL,'',NULL,''),
(1092,'黑名单查询',1071,4,'',NULL,1,'F','0','monitor:blacklist:query','#','admin','2019-11-01 17:32:24','',NULL,'',NULL,''),
(1093,'轮播图管理',1,7,'/system/carousel','system/carousel/index',1,'C','0','system:carousel:list','dict','admin','2019-11-04 11:11:02','',NULL,'',NULL,''),
(1094,'实时日志',2,6,'/monitor/log','monitor/RealTimeLog/index',1,'C','0',NULL,'education','admin','2019-11-05 18:16:20','',NULL,'',NULL,''),
(1095,'系统工具',0,2,'tool',NULL,1,'M','0',NULL,'education','admin','2019-11-06 10:53:11','',NULL,'',NULL,''),
(1097,'存储管理',1095,2,'tool/storage','tool/storage/index',1,'C','0','tool:storage:list','eye-open','admin','2019-11-06 10:54:28','admin','2019-11-07 12:07:52','',NULL,''),
(1098,'图床管理',1095,3,'tool/pictures','tool/pictures/index',1,'C','0','tool:pictures:list','date','admin','2019-11-06 10:55:34','admin','2019-11-06 10:58:18','',NULL,''),
(1099,'任务日志',108,3,'log/quartz','log/quartzLog/index',1,'C','0','','dict','admin','2019-11-08 16:23:22','admin','2019-11-08 16:23:37','',NULL,''),
(1100,'标签管理',1066,6,'/blog/tag','blog/tag/index',1,'C','0','','chart','admin','2019-11-13 11:03:47','admin','2019-11-22 12:18:08','',NULL,''),
(1101,'访问日志',108,6,'log/visitLog','log/visitLog',1,'C','0','log:visitLog:list','#','admin','2019-12-16 16:52:27','',NULL,'',NULL,''),
(1102,'图书管理',0,2,'book',NULL,1,'M','0','','post','admin','2019-12-17 09:47:02','admin','2019-12-17 13:38:01','',NULL,''),
(1103,'图书管理',1102,1,'book','book/book/index',1,'C','0','','education','admin','2019-12-17 09:49:20','admin','2019-12-17 14:22:23','',NULL,''),
(1104,'图书分类管理',1102,2,'category','book/category/index',1,'C','0','','date','admin','2019-12-17 09:50:47','admin','2019-12-17 09:50:57','',NULL,''),
(1105,'笔记管理',1102,1,'note','book/note/index',1,'C','0','','exit-fullscreen','admin','2019-12-17 09:51:28','admin','2019-12-17 09:51:39','',NULL,''),
(1106,'标签管理',1102,3,'tag','book/tag/index',1,'C','0','','forward','admin','2019-12-17 09:52:19','admin','2019-12-17 11:16:55','',NULL,''),
(1107,'新增笔记',1105,1,'book/note/add','book/note/add',1,'C','0','','dict','admin','2019-12-17 15:28:29','admin','2019-12-17 15:31:40','admin','2019-12-17 15:35:09','');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
                              `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
                              `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
                              `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
                              `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
                              `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                              `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                              `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                              `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'测试通知','2','1111111111111111<img style=\"width: 100%;height: 100%;\" src=\"http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9\">','0','admin','2018-03-16 11:33:00','admin','2019-10-10 15:58:20',NULL,NULL,NULL),
(2,'维护通知','1','维护内容<img style=\"width: 100%;height: 100%;\" src=\"http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9\">','0','admin','2018-03-16 11:33:00','admikn','2018-03-16 11:33:00',NULL,NULL,NULL),
(3,'1111111111','1','<p>11111111111111</p>','0','admin','2019-11-12 18:28:04','',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_operate_log` */

DROP TABLE IF EXISTS `sys_operate_log`;

CREATE TABLE `sys_operate_log` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                   `title` varchar(50) DEFAULT '' COMMENT '模块标题',
                                   `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                   `method` varchar(100) DEFAULT '' COMMENT '方法名称',
                                   `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
                                   `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                   `operate_name` varchar(50) DEFAULT '' COMMENT '操作人员',
                                   `url` varchar(255) DEFAULT '' COMMENT '请求URL',
                                   `ip` varchar(50) DEFAULT '' COMMENT '主机地址',
                                   `location` varchar(255) DEFAULT '' COMMENT '操作地点',
                                   `param` varchar(2000) DEFAULT '' COMMENT '请求参数',
                                   `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
                                   `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
                                   `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
                                   `cost` bigint(20) DEFAULT NULL COMMENT '耗时,单位毫秒',
                                   `create_time` datetime DEFAULT NULL COMMENT '操作时间',
                                   `create_by` varchar(128) DEFAULT NULL,
                                   `delete_by` varchar(128) DEFAULT NULL,
                                   `delete_time` datetime DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `sys_operate_log` */

insert  into `sys_operate_log`(`id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`operate_name`,`url`,`ip`,`location`,`param`,`json_result`,`status`,`error_msg`,`cost`,`create_time`,`create_by`,`delete_by`,`delete_time`) values
(103,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"title1\",\"title2\",\"6\",\"title4\"],\"title\":\"222222222222222222\",\"content\":\"<p>222222222222222222222222222222222</p>\",\"updateBy\":\"admin\",\"id\":5,\"summary\":\"22222222222222222222\",\"weight\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1574150188000,\"comment\":true,\"support\":true,\"categoryId\":937,\"headerImg\":\"http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9\",\"status\":true}','null',0,'nested exception is org.apache.ibatis.executor.ExecutorException: Error getting generated key or setting result to parameter object. Cause: org.apache.ibatis.executor.ExecutorException: Could not determine which parameter to assign generated keys to. Note that when there are multiple parameters, \'keyProperty\' must include the parameter name (e.g. \'param.id\'). Specified key properties are [id] and available parameters are [tagId, blogId, param1, param2]',191,'2019-11-22 18:03:48',NULL,'admin','2019-12-12 16:26:10'),
(104,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"title1\",\"title2\",\"3\"],\"title\":\"222222222222222222\",\"content\":\"<p>222222222222222222222222222222222</p>\",\"updateBy\":\"admin\",\"id\":5,\"summary\":\"22222222222222222222\",\"weight\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1574150188000,\"comment\":true,\"support\":true,\"categoryId\":937,\"headerImg\":\"http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9\",\"status\":true}','null',0,'nested exception is org.apache.ibatis.executor.ExecutorException: Error getting generated key or setting result to parameter object. Cause: org.apache.ibatis.executor.ExecutorException: Could not determine which parameter to assign generated keys to. Note that when there are multiple parameters, \'keyProperty\' must include the parameter name (e.g. \'param.id\'). Specified key properties are [id] and available parameters are [tagId, blogId, param1, param2]',25323,'2019-11-22 18:06:03',NULL,'admin','2019-12-12 16:26:10'),
(105,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"title1\",\"title4\",\"33\"],\"title\":\"222222222222222222\",\"content\":\"<p>222222222222222222222222222222222</p>\",\"updateBy\":\"admin\",\"id\":5,\"summary\":\"22222222222222222222\",\"weight\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1574150188000,\"comment\":true,\"support\":true,\"categoryId\":937,\"headerImg\":\"http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,12871,'2019-11-22 18:12:46',NULL,'admin','2019-12-12 16:26:10'),
(106,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"title1\"],\"title\":\"222222222222222222\",\"content\":\"<p>222222222222222222222222222222222</p>\",\"updateBy\":\"admin\",\"id\":5,\"summary\":\"22222222222222222222\",\"weight\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1574150188000,\"comment\":true,\"support\":true,\"categoryId\":937,\"headerImg\":\"http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,5268,'2019-11-22 18:13:13',NULL,'admin','2019-12-12 16:26:10'),
(107,'标签管理',2,'com.dimple.project.blog.controller.TagController.edit()','PUT',1,'admin','/blog/tag','192.168.20.56','内网IP','{\"color\":\"rgba(143, 31, 31, 1)\",\"count\":0,\"params\":{},\"title\":\"title3\",\"createBy\":\"admin\",\"createTime\":1574396430000,\"id\":3}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,53,'2019-11-22 18:13:42',NULL,'admin','2019-12-12 16:26:10'),
(108,'博客管理',2,'com.dimple.project.blog.controller.BlogController.editSupport()','PUT',1,'admin','/blog/blog/support','192.168.20.56','内网IP','{\"updateBy\":\"admin\",\"id\":8,\"params\":{},\"support\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,49,'2019-11-22 18:15:44',NULL,'admin','2019-12-12 16:26:10'),
(109,'博客管理',2,'com.dimple.project.blog.controller.BlogController.editComment()','PUT',1,'admin','/blog/blog/comment','192.168.20.56','内网IP','{\"updateBy\":\"admin\",\"id\":8,\"params\":{},\"comment\":false}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,51,'2019-11-22 18:15:46',NULL,'admin','2019-12-12 16:26:10'),
(110,'博客管理',2,'com.dimple.project.blog.controller.BlogController.editComment()','PUT',1,'admin','/blog/blog/comment','192.168.20.56','内网IP','{\"updateBy\":\"admin\",\"id\":7,\"params\":{},\"comment\":false}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,43,'2019-11-22 18:15:49',NULL,'admin','2019-12-12 16:26:10'),
(111,'博客管理',2,'com.dimple.project.blog.controller.BlogController.editComment()','PUT',1,'admin','/blog/blog/comment','192.168.20.56','内网IP','{\"updateBy\":\"admin\",\"id\":6,\"params\":{},\"comment\":false}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,53,'2019-11-22 18:15:51',NULL,'admin','2019-12-12 16:26:10'),
(112,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[],\"title\":\"222222222222222222\",\"content\":\"<div class=\\\"detail\\\">\\n<article class=\\\"typo container article-main-content\\\">\\n<h1 id=\\\"1-externalappendonlymap\\\">1. ExternalAppendOnlyMap的定义</h1>\\n理。在ExternalAppendOnlyMap的字段中，SizeTrackingAppendOnlyMap类型的<code>currentMap</code>底层依旧使用了AppendOnlyMap来存放数据，它用于记录当前使用内存存储数据的字典，<code>spilledMaps</code>作为一个数组，内部记录了每次溢写内存数据时得到的DiskMapIterator迭代器，它们是ExternalAppendOnlyMap最终的字段，分别代表着内存数据和溢写数据。\\n<p>另外，ExternalAppendOnlyMap在溢写时也是按照批次进行刷盘处理，每个批次内的键值对数量由<code>serializerBatchSize</code>字段决定，默认为10000条刷一次盘。</p>\\n<h1 id=\\\"2\\\">2. 插入数据</h1>\\n<p>ExternalAppendOnlyMap提供了插入键值对数据的方法，其中<code>insert(...)</code>方法用于插入单条键值对</p>\\n<div class=\\\"linefeed with-global-toolsbar\\\">\\n<div class=\\\"toolsbar\\\"><span class=\\\"language\\\">org.apache.spark.util.collection.ExternalAppendOnlyMap#insert</span><button id=\\\"button-shownum\\\" class=\\\"shownum-class ivu-icon\\\" title=\\\"显示或隐藏行号\\\" type=\\\"button\\\" value=\\\"显示或隐藏行号\\\"></button><button id=\\\"button-theme\\\" class=\\\"theme-class ivu-icon\\\" title=\\\"切换代码明暗显示\\\" type=\\\"button\\\" value=\\\"切换代码明暗显示\\\"></button><button id=\\\"button-copy\\\" class=\\\"copy-class ivu-icon\\\" title=\\\"复制代码到剪切板\\\" type=\\\"button\\\" value=\\\"复制代码到剪切板\\\"></button><button id=\\\"button-break\\\" class=\\\"break-class ivu-icon\\\" title=\\\"代码自动换行\\\" type=\\\"button\\\" value=\\\"代码自动换行\\\"></button><button id=\\\"button-fold\\\" class=\\\"fold-class ivu-icon\\\" title=\\\"点击收起代码\\\" type=\\\"button\\\" value=\\\"点击收起代码\\\"></button></div>\\n<div class=\\\"highlight linefeed\\\">\\n<ul class=\\\"highlight-ul\\\">\\n<li class=\\\"numbered\\\"><span class=\\\"cm\\\">/**</span></li>\\n<li class=\\\"numbered\\\"><span class=\\\"cm\\\"> * Insert the given key and value into the map.</span></li>\\n<li class=\\\"numbered\\\"><span class=\\\"cm\\\"> * 插入键值对数据</span></li>\\n<li class=\\\"numbered\\\"><span class=\\\"cm\\\"> */</span></li>\\n<li class=\\\"numbered\\\"><span class=\\\"k\\\">def</span> <span class=\\\"n\\\">insert</span><span class=\\\"o\\\">(</span><span class=\\\"n\\\">key</span><span class=\\\"k\\\">:</span> <span class=\\\"kt\\\">K</span><span class=\\\"o\\','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,153,'2019-11-26 16:33:46',NULL,'admin','2019-12-12 16:26:10'),
(113,'分类管理',1,'com.dimple.project.blog.controller.CategoryController.add()','POST',1,'admin','/blog/category','192.168.20.56','内网IP','{\"description\":\"你知道的越多，你不知道的越多\",\"params\":{},\"title\":\"工具集合\",\"createBy\":\"admin\",\"id\":939,\"support\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,67,'2019-11-28 13:54:18',NULL,'admin','2019-12-12 16:26:10'),
(114,'博客管理',1,'com.dimple.project.blog.controller.BlogController.add()','POST',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"321311\"],\"title\":\"12313212\",\"content\":\"切尔奇二\",\"htmlContent\":\"<p>切尔奇二</p>\\n\",\"id\":21,\"summary\":\"122耳温枪二二二二二二二二二二二二\",\"weight\":4,\"params\":{},\"createBy\":\"admin\",\"comment\":true,\"support\":true,\"categoryId\":939,\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,142,'2019-11-28 14:03:52',NULL,'admin','2019-12-12 16:26:10'),
(115,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"整理\",\"工具集\",\"程序员\"],\"title\":\"《吐血整理》-顶级程序员工具集\",\"content\":\"# 前言\\n这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。\\n\\n主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！\\n\\n千万不要白嫖，真香警告⚠️。\\n\\n但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。\\n\\n因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？\\n\\n那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。\\n\\n注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版\\n# 正文\\n既然都说了帅丙是要用一个产品的研发流程角度去介绍软件，那我们先看看一个产品有哪些研发流程，帅丙就用自己接触的阿里系的研发流程举例了，这也基本上是互联网大厂的研发流程了，可能细节有出入，但是绝对大同小异。\\n**Tip**：我从开发的视角去介绍的话我就会跳过一些软件，比如提出需求这个其实一般都是文档类的，wiki呀这样的形式。\\n\\n我就不介绍那种流程工具了，公司很多自研的，大家工作了再去熟悉也很快的。\\n## 概要设计\\n概要设计，这个是大厂程序员需求下来之后基本上都会做的一步，不过看需求大小，可能很多小需求直接就详细设计了。\\n\\n很多不了解的同学可能会问，需要设计什么呢？为什么要设计呢？\\n\\n问得好，经常看我文章的都知道，技术是把双刃剑，你用了技术之后你是不是需要列出他的优点缺点，出问题之后的解决方案，还有可能出现的问题，注意点等等。\\n### 工具：Xmind/ProcessOn\\nXmind官网地址： https://www.xmind.cn\\nProcessOn在线作图地址：https://www.processon.com\\n\\n## 详细设计\\n小伙伴又要问了啥是详细设计呀帅丙？\\n\\n诶呀简单，见名知意嘛，概要设计是大概的设计，详细设计是详细的设计。\\n\\n我们研发的时候整个流程往往很复杂，如果你理解不对直接就写代码，最后容易造成返工，延期，加班，被骂，心情差，回家吵架，离家出走。。。\\n\\n看到不做详细设计的后果了吧，其实大家花点时间做详细设计很有必要，你思路完全清晰了，写代码那就是分分钟的事情，不是嘛？\\n\\n那再看看帅丙的一个小设计吧，之前文章中大量的流程图，时序图都来自它，主要是这玩意还是在线的，都不用下载很方便啊。\\n\\n详细设计的工具我用的就是在线作图神器：ProcessOn\\n在线画图地址：https://www.processon.com\\n\\n> 总之一句话很香，流程图、思维导图、原型图、UML、网络拓扑图、组织结构图、BPMN等等一应俱全\\n\\n# 研发\\n这个关键了：工欲善其事，必先利其器\\n\\n想必大家都知道这个谚语吧，我就说一下我写代码要用到的软件吧。\\n\\n\",\"htmlContent\":\"<h1 id=\\\"前言\\\">前言</h1>\\n<p>这期是被人才群交流里，还有很多之前网友评论强行顶出来的一期，就是让我介绍自己常用的一些工具给他们安利一下，我一听很高兴呀，帅丙我这么乐于奉献的人是吧。</p>\\n<p>主要是能水一篇文章就很开心，不过写下来发现花的时间完全不比写技术少，点赞！！！</p>\\n<p>千万不要白嫖，真香警告⚠️。</p>\\n<p>但是我在构思这篇文章的时候发现我贴个标题，然后发下软件信息会不会太乏味了，于是创作鬼才我呀，准备用一个产品的研发流程，是的就是用这样的一个思路去写这个工具集的介绍文章。</p>\\n<p>因为读者很多还是学生，还有很多应届生，对一个需求的研发流程都不是很熟悉，还有可能对于以后自己需要使用到的工具都不是很熟悉，那我就一一罗列一下，帅丙我作为一个还算有点小经验的程序员都使用哪些工具呢？</p>\\n<p>那下面就跟随暖男的脚步，走进顶级程序员的百宝箱吧（我所有的标题都是噱头就为了夸大其词，我是低级程序员，大家看了也不能吊打面试官，笑笑就好了）。</p>\\n<p>注意：下面的软件我都是简单的介绍下是干啥的，因为太多了，真正的功能需要大家深挖的，能力允许的朋友下载正版</p>\\n<h1 ','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,933,'2019-11-28 14:10:54',NULL,'admin','2019-12-12 16:26:10'),
(116,'分类管理',1,'com.dimple.project.blog.controller.CategoryController.add()','POST',1,'admin','/blog/category','192.168.20.56','内网IP','{\"description\":\"自动化测试相关教程1\",\"params\":{},\"title\":\"教程分类\",\"createBy\":\"admin\",\"id\":940,\"support\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,83,'2019-11-28 14:28:57',NULL,'admin','2019-12-12 16:26:10'),
(117,'博客管理',1,'com.dimple.project.blog.controller.BlogController.add()','POST',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"则提取关键字\",\"ID和tag name定位\",\"web页面元素\"],\"title\":\"《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）\",\"content\":\"# 1.简介\\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\\n\\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\\n\\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\\n\\n# 2. 练习场景：\\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\\n\\n# 3. 思路拆分：\\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\\n\\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\\n\\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\\n\\n# 4.技术角度实现相关方法：\\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\\n\\n2. Python中利用正则，需要导入re模块\\n\\n3. 将字段通过下列代码打印出来\\n\\n```\\nfor email in emails :\\n\\n          print email\\n\\n```\\n\\n## 4.1 代码实现\\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\\n\\n\\n\\n## 4.2 参考代码：\\n```\\n# coding=utf-8\\n\\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\\n\\n# 2.注释：包括记录创建时间，创建人，项目名称。\\n\'\'\'\\nCreated on 2019-11-28\\n@author: 北京-宏哥   QQ交流群：705269076\\nProject: python+ selenium自动化测试练习篇\\n\'\'\'\\n\\n# 3.导入模块\\nfrom selenium import webdriver\\nimport re\\n\\ndriver = webdriver.Chrome()\\ndriver.maximize_window()\\ndriver.implicitly_wait(6)\\n\\ndriver.get(\\\"http://home.baidu.com/contact.html\\\")\\n# 得到页面源代码\\ndoc = driver.page_source\\nemails = re.findall(r\'[\\\\w]+@[\\\\w\\\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\\n# 循环打印匹配的邮箱\\nfor email in emails:\\n    print(email)\\n\\n```\\n\\n## 4.3 解释说明：\\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\\n\\n## 4.4 运行结果：\\n运行代码后，控制台打印如下图的结果\\n\\n\\n\\n# 5.利用ID定位元素\\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\\n\\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\\n\\n## 5.1 代码实现\\n我们新建一个test_baidu_id.py文件，输入如下代码：\\n\\n\\n\\n## 5.2 参考代码\\n```\\n# coding=utf-','null',0,'\r\n### Error updating database.  Cause: java.sql.SQLException: Incorrect string value: \'\\xF0\\x9F\\x94\\xA5\\x0A\\x0A...\' for column \'content\' at row 1\r\n### The error may exist in file [E:\\Code\\SpringBoot\\DimpleBlog\\target\\classes\\mybatis\\blog\\BlogMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: insert into bg_blog          ( category_id,             title,             summary,                          content,             html_content,             status,             comment,             support,             weight,             create_by,             create_time )           values ( ?,             ?,             ?,                          ?,             ?,             ?,             ?,             ?,             ?,             ?,             sysdate() )\r\n### Cause: java.sql.SQLException: Incorrect string value: \'\\xF0\\x9F\\x94\\xA5\\x0A\\x0A...\' for column \'content\' at row 1\n; uncategorized SQLException; SQL state [HY000]; error code [1366]; Incorrect string value: \'\\xF0\\x9F\\x94\\xA5\\x0A\\x0A...\' for column \'content\' at row 1; nested exception is java.sql.SQLException: Incorrect string value: \'\\xF0\\x9F\\x94\\xA5\\x0A\\x0A...\' for column \'content\' at row 1',4,'2019-11-28 14:44:16',NULL,'admin','2019-12-12 16:26:10'),
(118,'博客管理',1,'com.dimple.project.blog.controller.BlogController.add()','POST',1,'admin','/blog/blog','192.168.20.56','内网IP','{\"tagTitleList\":[\"则提取关键字\",\"ID和tag name定位\",\"web页面元素\"],\"title\":\"《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）\",\"content\":\"# 1.简介\\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\\n\\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\\n\\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\\n\\n# 2. 练习场景：\\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\\n\\n# 3. 思路拆分：\\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\\n\\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\\n\\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\\n\\n# 4.技术角度实现相关方法：\\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\\n\\n2. Python中利用正则，需要导入re模块\\n\\n3. 将字段通过下列代码打印出来\\n\\n```\\nfor email in emails :\\n\\n          print email\\n\\n```\\n\\n## 4.1 代码实现\\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\\n\\n\\n\\n## 4.2 参考代码：\\n```\\n# coding=utf-8\\n\\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\\n\\n# 2.注释：包括记录创建时间，创建人，项目名称。\\n\'\'\'\\nCreated on 2019-11-28\\n@author: 北京-宏哥   QQ交流群：705269076\\nProject: python+ selenium自动化测试练习篇\\n\'\'\'\\n\\n# 3.导入模块\\nfrom selenium import webdriver\\nimport re\\n\\ndriver = webdriver.Chrome()\\ndriver.maximize_window()\\ndriver.implicitly_wait(6)\\n\\ndriver.get(\\\"http://home.baidu.com/contact.html\\\")\\n# 得到页面源代码\\ndoc = driver.page_source\\nemails = re.findall(r\'[\\\\w]+@[\\\\w\\\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\\n# 循环打印匹配的邮箱\\nfor email in emails:\\n    print(email)\\n\\n```\\n\\n## 4.3 解释说明：\\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\\n\\n## 4.4 运行结果：\\n运行代码后，控制台打印如下图的结果\\n\\n\\n\\n# 5.利用ID定位元素\\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\\n\\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\\n\\n## 5.1 代码实现\\n我们新建一个test_baidu_id.py文件，输入如下代码：\\n\\n\\n\\n## 5.2 参考代码\\n```\\n# coding=utf-','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,89,'2019-11-28 14:44:31',NULL,'admin','2019-12-12 16:26:10'),
(119,'个人信息',2,'com.dimple.project.system.controller.ProfileController.updatePwd()','PUT',1,'admin','/system/user/profile/updatePwd','222.209.155.52','中国|四川省|成都市|电信','admin123 bxf960805','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,284,'2019-11-28 22:26:39',NULL,'admin','2019-12-12 16:26:10'),
(120,'个人信息',2,'com.dimple.project.system.controller.ProfileController.updateProfile()','PUT',1,'admin','/system/user/profile','222.209.155.52','中国|四川省|成都市|电信','{\"nickName\":\"Dimple\",\"roles\":[{\"flag\":false,\"admin\":true,\"params\":{},\"roleSort\":\"1\",\"roleKey\":\"admin\",\"roleName\":\"管理员\",\"id\":1,\"status\":\"0\"}],\"sex\":\"0\",\"admin\":true,\"loginDate\":1521171180000,\"remark\":\"管理员\",\"avatar\":\"hthz_9.jpeg\",\"params\":{},\"userName\":\"admin\",\"createBy\":\"admin\",\"password\":\"$2a$10$Pb9xMxuha0/k5bvCqx9EPOWmPCf91dpqUKVgjQu.ydk6bRUVktl3q\",\"createTime\":1521171180000,\"phone\":\"15555555555\",\"loginIp\":\"127.0.0.1\",\"id\":1,\"email\":\"bianxiaofeng@sohu.com\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,26,'2019-11-28 22:27:20',NULL,'admin','2019-12-12 16:26:10'),
(121,'个人信息',2,'com.dimple.project.system.controller.ProfileController.updateProfile()','PUT',1,'admin','/system/user/profile','222.209.155.52','中国|四川省|成都市|电信','{\"admin\":true,\"avatar\":\"lm_12.jpg\",\"params\":{},\"id\":1}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,33,'2019-11-28 22:27:28',NULL,'admin','2019-12-12 16:26:10'),
(122,'博客管理',2,'com.dimple.project.blog.controller.BlogController.editComment()','PUT',1,'admin','/blog/blog/comment','222.209.155.52','中国|四川省|成都市|电信','{\"updateBy\":\"admin\",\"id\":33,\"params\":{},\"comment\":false}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,27,'2019-11-28 23:18:33',NULL,'admin','2019-12-12 16:26:10'),
(123,'博客管理',2,'com.dimple.project.blog.controller.BlogController.editSupport()','PUT',1,'admin','/blog/blog/support','222.209.155.52','中国|四川省|成都市|电信','{\"updateBy\":\"admin\",\"id\":30,\"params\":{},\"support\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,16,'2019-11-28 23:18:37',NULL,'admin','2019-12-12 16:26:10'),
(124,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','222.209.155.52','中国|四川省|成都市|电信','{\"tagTitleList\":[],\"title\":\"《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）\",\"content\":\"# 1.简介\\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\\n\\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\\n\\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\\n\\n# 2. 练习场景：\\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\\n\\n# 3. 思路拆分：\\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\\n\\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\\n\\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\\n\\n# 4.技术角度实现相关方法：\\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\\n\\n2. Python中利用正则，需要导入re模块\\n\\n3. 将字段通过下列代码打印出来\\n\\n```\\nfor email in emails :\\n\\n          print email\\n\\n```\\n\\n## 4.1 代码实现\\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\\n\\n\\n\\n## 4.2 参考代码：\\n```\\n# coding=utf-8\\n\\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\\n\\n# 2.注释：包括记录创建时间，创建人，项目名称。\\n\'\'\'\\nCreated on 2019-11-28\\n@author: 北京-宏哥   QQ交流群：705269076\\nProject: python+ selenium自动化测试练习篇\\n\'\'\'\\n\\n# 3.导入模块\\nfrom selenium import webdriver\\nimport re\\n\\ndriver = webdriver.Chrome()\\ndriver.maximize_window()\\ndriver.implicitly_wait(6)\\n\\ndriver.get(\\\"http://home.baidu.com/contact.html\\\")\\n# 得到页面源代码\\ndoc = driver.page_source\\nemails = re.findall(r\'[\\\\w]+@[\\\\w\\\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\\n# 循环打印匹配的邮箱\\nfor email in emails:\\n    print(email)\\n\\n```\\n\\n## 4.3 解释说明：\\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\\n\\n## 4.4 运行结果：\\n运行代码后，控制台打印如下图的结果\\n\\n\\n\\n# 5.利用ID定位元素\\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\\n\\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\\n\\n## 5.1 代码实现\\n我们新建一个test_baidu_id.py文件，输入如下代码：\\n\\n\\n\\n## 5.2 参考代码\\n```\\n# coding=utf-8\\n\\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,20,'2019-11-28 23:18:47',NULL,'admin','2019-12-12 16:26:10'),
(125,'博客管理',2,'com.dimple.project.blog.controller.BlogController.edit()','PUT',1,'admin','/blog/blog','192.168.13.145','内网IP','{\"tagTitleList\":[],\"title\":\"《手把手教你》系列练习篇之1-python+ selenium自动化测试（详细教程）\",\"content\":\"# 1.简介\\n　　相信各位小伙伴或者同学们通过前面已经介绍了的Python+Selenium基础篇，通过前面几篇文章的介绍和练习,Selenium+Python的webUI自动化测试算是 一只脚已经迈入这个门槛了要想第二只脚也迈进来。那么就要继续跟随宏哥的脚步继续前行。接下来，宏哥\\n\\n计划写第二个系列：练习篇，通过一些练习，了解和掌握一些Selenium常用的接口或者方法。同时也可以把各位小伙伴或者童鞋们的基础夯实一下、巩固一下。这样有助于小伙伴或者同学们更快的将自己在门外的第二只脚迈进来，加入自动化测试的队伍中。\\n\\n　　本文通过练习三个知识点：正则提取关键字、ID和tag name定位web页面元素。\\n\\n# 2. 练习场景：\\n　　例如：在某一个网页上有些字段或者关键字等信息是我们感兴趣的，我们希望将其摘取出来，进行其他操作。但是这些字段可能在一个网页的不同地方。例如，我们需要在关于百度页面-联系我们，摘取全部的邮箱。\\n\\n# 3. 思路拆分：\\n1. 首先，需要得到当前页面的source内容，就像，打开一个页面，右键-查看页面源代码。\\n\\n2. 找出规律，通过正则表达式去摘取匹配的字段，存储到一个字典或者列表。\\n\\n3. 循环打印字典或列表中内容，Python中用 for 语句实现。\\n\\n# 4.技术角度实现相关方法：\\n1. 查看页面的源代码，在Selenium中有driver.page_source 这个方法得到\\n\\n2. Python中利用正则，需要导入re模块\\n\\n3. 将字段通过下列代码打印出来\\n\\n```\\nfor email in emails :\\n\\n          print email\\n\\n```\\n\\n## 4.1 代码实现\\n想法技术角度方法都找到，我们新建一个extract_email.py 文件，输入如下代码:\\n\\n\\n\\n## 4.2 参考代码：\\n```\\n# coding=utf-8\\n\\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在第一行\\n\\n# 2.注释：包括记录创建时间，创建人，项目名称。\\n\'\'\'\\nCreated on 2019-11-28\\n@author: 北京-宏哥   QQ交流群：705269076\\nProject: python+ selenium自动化测试练习篇\\n\'\'\'\\n\\n# 3.导入模块\\nfrom selenium import webdriver\\nimport re\\n\\ndriver = webdriver.Chrome()\\ndriver.maximize_window()\\ndriver.implicitly_wait(6)\\n\\ndriver.get(\\\"http://home.baidu.com/contact.html\\\")\\n# 得到页面源代码\\ndoc = driver.page_source\\nemails = re.findall(r\'[\\\\w]+@[\\\\w\\\\.-]+\', doc)  # 利用正则，找出 xxx@xxx.xxx 的字段，保存到emails列表\\n# 循环打印匹配的邮箱\\nfor email in emails:\\n    print(email)\\n\\n```\\n\\n## 4.3 解释说明：\\n在python正则表达式语法中，Python中字符串前面加上 r 表示原生字符串，用\\\\w表示匹配字母数字及下划线。re模块下findall方法返回的是一个匹配子字符串的列表。\\n\\n## 4.4 运行结果：\\n运行代码后，控制台打印如下图的结果\\n\\n\\n\\n# 5.利用ID定位元素\\n　　在上边，我们介绍了如何摘取页面字段，通过正则进行匹配符合要求的字段。如果感觉有点困难，不能立马理解，没有关系。把字符串摘取放到第一篇，是因为自动化测试脚本，经常要利用字符串操作，字符串切割，查找，匹配等手段，得到新的字符串或字符串数组，然后根据新得到的字符串进行判断用例是否通过。\\n\\n      下面介绍如何通过元素节点信息ID来定位该元素，使用id来定位元素虽然效率要高于XPath，但是实际测试测项目，能直接通过id定位的元素还是比较少，以下来举例百度首页搜索输入框的id定位。\\n\\n## 5.1 代码实现\\n我们新建一个test_baidu_id.py文件，输入如下代码：\\n\\n\\n\\n## 5.2 参考代码\\n```\\n# coding=utf-8\\n\\n# 1.先设置编码，utf-8可支持中英文，如上，一般放在','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,142,'2019-12-11 16:17:37',NULL,'admin','2019-12-12 16:26:10'),
(126,'本地存储',10,'com.dimple.project.tool.controller.LocalStorageController.upload()','POST',1,'admin','/tool/localStorage','192.168.13.145','内网IP','','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,215,'2019-12-12 09:41:04',NULL,'admin','2019-12-12 16:26:10'),
(127,'本地存储',10,'com.dimple.project.tool.controller.LocalStorageController.upload()','POST',1,'admin','/tool/localStorage','192.168.13.145','内网IP','','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,137,'2019-12-12 09:49:22',NULL,'admin','2019-12-12 16:26:10'),
(128,'本地存储',10,'com.dimple.project.tool.controller.LocalStorageController.upload()','POST',1,'admin','/tool/localStorage','192.168.13.145','内网IP','','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,59,'2019-12-12 11:25:59',NULL,'admin','2019-12-12 16:26:10'),
(129,'本地存储',10,'com.dimple.project.tool.controller.LocalStorageController.upload()','POST',1,'admin','/tool/localStorage','192.168.13.145','内网IP','','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,74,'2019-12-12 11:26:04',NULL,'admin','2019-12-12 16:26:10'),
(130,'七牛云存储',10,'com.dimple.project.tool.controller.QiNiuController.upload()','POST',1,'admin','/tool/qiNiu','192.168.13.145','内网IP','','null',0,'',80421,'2019-12-12 14:56:23',NULL,'admin','2019-12-12 16:26:10'),
(131,'七牛云存储',10,'com.dimple.project.tool.controller.QiNiuController.upload()','POST',1,'admin','/tool/qiNiu','192.168.13.145','内网IP','','null',0,'',200022,'2019-12-12 14:58:23',NULL,'admin','2019-12-12 16:26:10'),
(132,'七牛云存储',10,'com.dimple.project.tool.controller.QiNiuController.upload()','POST',1,'admin','/tool/qiNiu','192.168.13.145','内网IP','','null',0,'',200113,'2019-12-12 15:17:19',NULL,'admin','2019-12-12 16:26:10'),
(133,'个人信息',2,'com.dimple.project.system.controller.ProfileController.updateProfile()','PUT',1,'admin','/system/user/profile','192.168.13.145','内网IP','{\"admin\":true,\"avatar\":\"lm_12.jpg\",\"params\":{},\"id\":1}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,61,'2019-12-12 16:22:10',NULL,'admin','2019-12-12 16:26:10'),
(134,'个人信息',2,'com.dimple.project.system.controller.ProfileController.updateProfile()','PUT',1,'admin','/system/user/profile','192.168.13.145','内网IP','{\"admin\":true,\"avatar\":\"lm_5.jpg\",\"params\":{},\"id\":1}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,54,'2019-12-12 16:22:12',NULL,'admin','2019-12-12 16:26:10'),
(135,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"orderNum\":\"6\",\"menuName\":\"访问日志\",\"params\":{},\"parentId\":108,\"path\":\"log/visitLog\",\"component\":\"log/visitLog\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"log:visitLog:list\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,57,'2019-12-16 16:52:27',NULL,NULL,NULL),
(136,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"post\",\"orderNum\":\"2\",\"menuName\":\"图书管理\",\"params\":{},\"path\":\"n\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,68,'2019-12-17 09:47:02',NULL,NULL,NULL),
(137,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"education\",\"orderNum\":\"1\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":1102,\"path\":\"11\",\"component\":\"book/book/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,52,'2019-12-17 09:49:20',NULL,NULL,NULL),
(138,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"education\",\"orderNum\":\"1\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":1102,\"path\":\"/book/book\",\"component\":\"book/book/index\",\"children\":[],\"createTime\":1576547360000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1103}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,72,'2019-12-17 09:49:39',NULL,NULL,NULL),
(139,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"date\",\"orderNum\":\"2\",\"menuName\":\"图书分类管理\",\"params\":{},\"parentId\":1102,\"path\":\"/\",\"component\":\"book/category/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,47,'2019-12-17 09:50:47',NULL,NULL,NULL),
(140,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"date\",\"orderNum\":\"2\",\"menuName\":\"图书分类管理\",\"params\":{},\"parentId\":1102,\"path\":\"/book/category\",\"component\":\"book/category/index\",\"children\":[],\"createTime\":1576547447000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1104}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,64,'2019-12-17 09:50:57',NULL,NULL,NULL),
(141,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"exit-fullscreen\",\"orderNum\":\"1\",\"menuName\":\"笔记管理\",\"params\":{},\"parentId\":1102,\"path\":\"/\",\"component\":\"book/note/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,49,'2019-12-17 09:51:28',NULL,NULL,NULL),
(142,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"exit-fullscreen\",\"orderNum\":\"1\",\"menuName\":\"笔记管理\",\"params\":{},\"parentId\":1102,\"path\":\"/book/note\",\"component\":\"book/note/index\",\"children\":[],\"createTime\":1576547488000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1105}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,59,'2019-12-17 09:51:39',NULL,NULL,NULL),
(143,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"forward\",\"orderNum\":\"3\",\"menuName\":\"标签管理\",\"params\":{},\"parentId\":1102,\"path\":\"/d\",\"component\":\"book/tag/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,52,'2019-12-17 09:52:19',NULL,NULL,NULL),
(144,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"forward\",\"orderNum\":\"3\",\"menuName\":\"标签管理\",\"params\":{},\"parentId\":1102,\"path\":\"/book/tag\",\"component\":\"book/tag/index\",\"children\":[],\"createTime\":1576547539000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1106}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,48,'2019-12-17 09:52:31',NULL,NULL,NULL),
(145,'分类管理',1,'com.dimple.project.book.controller.BookCategoryController.add()','POST',1,'admin','/book/category','192.168.13.145','内网IP','{\"createBy\":\"admin\",\"description\":\"11111111111111111111\",\"id\":1,\"params\":{},\"title\":\"111111111111\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,62,'2019-12-17 09:56:48',NULL,NULL,NULL),
(146,'分类管理',3,'com.dimple.project.book.controller.BookCategoryController.remove()','DELETE',1,'admin','/book/category/1','192.168.13.145','内网IP','{ids=1}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,88,'2019-12-17 09:56:51',NULL,NULL,NULL),
(147,'分类管理',1,'com.dimple.project.blog.controller.CategoryController.add()','POST',1,'admin','/blog/category','192.168.13.145','内网IP','{\"description\":\"少时诵诗书所所所所所所所所所所\",\"params\":{},\"title\":\"111111111\",\"createBy\":\"admin\",\"id\":941,\"support\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,55,'2019-12-17 10:21:05',NULL,NULL,NULL),
(148,'分类管理',1,'com.dimple.project.book.controller.BookCategoryController.add()','POST',1,'admin','/book/category','192.168.13.145','内网IP','{\"createBy\":\"admin\",\"description\":\"111111111111111111111111\",\"id\":2,\"params\":{},\"title\":\"11111111111\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,44,'2019-12-17 10:21:46',NULL,NULL,NULL),
(149,'图书管理',1,'com.dimple.project.book.controller.BookController.add()','POST',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"11111111111111111\",\"description\":\"11111111111111111111\",\"title\":\"111111111111111111\",\"id\":1,\"author\":\"1111111111111111111111\",\"params\":{},\"pageNum\":11,\"createBy\":\"admin\",\"grade\":4.0,\"progress\":74,\"publisher\":\"1111111111111\",\"support\":true,\"headerImg\":\"111111111111111111\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,100,'2019-12-17 10:28:20',NULL,NULL,NULL),
(150,'图书管理',2,'com.dimple.project.book.controller.BookController.edit()','PUT',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"11111111111111111\",\"description\":\"11111111111111111111\",\"title\":\"111111111111111111\",\"id\":1,\"publishTime\":1575475200000,\"like\":0,\"author\":\"1111111111111111111111\",\"params\":{},\"click\":0,\"pageNum\":11,\"createTime\":1576549700000,\"grade\":4.0,\"progress\":74,\"publisher\":\"1111111111111\",\"support\":true,\"headerImg\":\"111111111111111111\",\"status\":true}','null',0,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'like = 0,\n            \n            \n                status = 1,\n            \n   \' at line 33\r\n### The error may exist in file [E:\\Code\\SpringBoot\\DimpleBlog\\target\\classes\\mybatis\\book\\BookMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: update bk_book          SET title = ?,                                           header_img = ?,                                           author = ?,                                                        publisher = ?,                                           publish_time = ?,                                           page_num = ?,                                           grade = ?,                                           description = ?,                                           catalog = ?,                                           click = ?,                                           like = ?,                                           status = ?,                                           progress = ?,                                           support = ?,                                       update_time=sysdate()          where id = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'like = 0,\n            \n            \n                status = 1,\n            \n   \' at line 33\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'like = 0,\n            \n            \n                status = 1,\n            \n   \' at line 33',61,'2019-12-17 10:38:16',NULL,NULL,NULL),
(151,'图书管理',2,'com.dimple.project.book.controller.BookController.edit()','PUT',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"11111111111111111\",\"description\":\"11111111111111111111\",\"title\":\"111111111111111111\",\"id\":1,\"like\":0,\"author\":\"1111111111111111111111\",\"params\":{},\"click\":0,\"pageNum\":11,\"createTime\":1576549700000,\"grade\":4,\"progress\":74,\"publisher\":\"1111111111111\",\"support\":true,\"headerImg\":\"111111111111111111\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,81,'2019-12-17 10:43:07',NULL,NULL,NULL),
(152,'图书管理',2,'com.dimple.project.book.controller.BookController.edit()','PUT',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"11111111111111111\",\"description\":\"11111111111111111111\",\"title\":\"111111111111111111\",\"id\":1,\"publishTime\":1551398400000,\"like\":0,\"author\":\"1111111111111111111111\",\"params\":{},\"click\":0,\"pageNum\":11,\"createTime\":1576549700000,\"grade\":4,\"progress\":74,\"publisher\":\"1111111111111\",\"support\":true,\"headerImg\":\"111111111111111111\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,129,'2019-12-17 10:45:46',NULL,NULL,NULL),
(153,'图书管理',2,'com.dimple.project.book.controller.BookController.edit()','PUT',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"11111111111111111\",\"description\":\"11111111111111111111\",\"title\":\"111111111111111111\",\"id\":1,\"publishTime\":1552003200000,\"like\":0,\"author\":\"1111111111111111111111\",\"params\":{},\"click\":0,\"pageNum\":11,\"createTime\":1576549700000,\"grade\":4,\"progress\":74,\"publisher\":\"1111111111111\",\"support\":true,\"headerImg\":\"111111111111111111\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,68,'2019-12-17 10:45:52',NULL,NULL,NULL),
(154,'图书管理',1,'com.dimple.project.book.controller.BookController.add()','POST',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"1111\",\"description\":\"111111111111111111111111\",\"title\":\"1111\",\"publishTime\":1575504000000,\"author\":\"111111111111111\",\"params\":{},\"pageNum\":11,\"createBy\":\"admin\",\"grade\":5,\"progress\":11,\"publisher\":\"11111111111111111\",\"headerImg\":\"111111111111111111\"}','null',0,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### The error may exist in file [E:\\Code\\SpringBoot\\DimpleBlog\\target\\classes\\mybatis\\book\\BookMapper.xml]\r\n### The error may involve com.dimple.project.book.mapper.BookMapper.insertBook\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String',7,'2019-12-17 10:46:18',NULL,NULL,NULL),
(155,'图书管理',1,'com.dimple.project.book.controller.BookController.add()','POST',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"1111\",\"description\":\"111111111111111111111111\",\"title\":\"1111\",\"id\":2,\"publishTime\":1575504000000,\"author\":\"111111111111111\",\"params\":{},\"pageNum\":11,\"createBy\":\"admin\",\"grade\":5,\"progress\":11,\"publisher\":\"11111111111111111\",\"headerImg\":\"111111111111111111\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,102,'2019-12-17 10:47:54',NULL,NULL,NULL),
(156,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"forward\",\"orderNum\":\"3\",\"menuName\":\"标签管理\",\"params\":{},\"parentId\":1102,\"path\":\"book/tag\",\"component\":\"book/tag/index\",\"children\":[],\"createTime\":1576547539000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1106}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,84,'2019-12-17 11:16:55',NULL,NULL,NULL),
(157,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"education\",\"orderNum\":\"1\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":1102,\"path\":\"book/book\",\"component\":\"book/book/index\",\"children\":[],\"createTime\":1576547360000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1103}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,73,'2019-12-17 11:17:05',NULL,NULL,NULL),
(158,'图书管理',3,'com.dimple.project.book.controller.BookController.remove()','DELETE',1,'admin','/book/book/1','192.168.13.145','内网IP','{ids=1}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,53,'2019-12-17 11:17:51',NULL,NULL,NULL),
(159,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"post\",\"orderNum\":\"2\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":0,\"path\":\"\",\"children\":[],\"createTime\":1576547222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"M\",\"perms\":\"\",\"id\":1102}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,52,'2019-12-17 11:19:36',NULL,NULL,NULL),
(160,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"post\",\"orderNum\":\"2\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":0,\"path\":\"\",\"children\":[],\"createTime\":1576547222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"M\",\"perms\":\"\",\"id\":1102}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,43,'2019-12-17 13:37:56',NULL,NULL,NULL),
(161,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"post\",\"orderNum\":\"2\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":0,\"path\":\"\",\"children\":[],\"createTime\":1576547222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"M\",\"perms\":\"\",\"id\":1102}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,69,'2019-12-17 13:38:01',NULL,NULL,NULL),
(162,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"education\",\"orderNum\":\"1\",\"menuName\":\"图书\",\"params\":{},\"parentId\":1102,\"path\":\"book/book\",\"component\":\"book/book/index\",\"children\":[],\"createTime\":1576547360000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1103}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,60,'2019-12-17 13:38:28',NULL,NULL,NULL),
(163,'标签管理',1,'com.dimple.project.book.controller.NoteTagController.add()','POST',1,'admin','/book/tag','192.168.13.145','内网IP','{\"color\":\"rgba(173, 57, 57, 1)\",\"params\":{},\"title\":\"1111111111\",\"createBy\":\"admin\",\"id\":1}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,87,'2019-12-17 13:52:53',NULL,NULL,NULL),
(164,'图书管理',2,'com.dimple.project.book.controller.BookController.edit()','PUT',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"1111\",\"description\":\"111111111111111111111111\",\"title\":\"1111\",\"id\":2,\"publishTime\":1575504000000,\"like\":0,\"author\":\"111111111111111\",\"params\":{},\"click\":0,\"pageNum\":11,\"createTime\":1576550874000,\"grade\":5,\"progress\":11,\"publisher\":\"11111111111111111\",\"support\":false,\"headerImg\":\"http://image.banshujiang.cn/1911.jpeg\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,70,'2019-12-17 14:08:50',NULL,NULL,NULL),
(165,'图书管理',2,'com.dimple.project.book.controller.BookController.edit()','PUT',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"1111\",\"description\":\"111111111111111111111111\",\"title\":\"1111\",\"id\":2,\"publishTime\":1575936000000,\"like\":0,\"author\":\"111111111111111\",\"params\":{},\"click\":0,\"pageNum\":11,\"createTime\":1576550874000,\"grade\":5,\"progress\":11,\"publisher\":\"11111111111111111\",\"support\":false,\"headerImg\":\"http://image.banshujiang.cn/1911.jpeg\",\"status\":true}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,49,'2019-12-17 14:08:54',NULL,NULL,NULL),
(166,'图书管理',1,'com.dimple.project.book.controller.BookController.add()','POST',1,'admin','/book/book','192.168.13.145','内网IP','{\"catalog\":\"前言\\n第一部分走近Java\\n第1章走近Java2\\n1.1概述2\\n1.2Java技术体系3\\n1.3Java发展史5\\n1.4Java虚拟机发展史9\\n1.4.1SunClassicExactVM9\\n1.4.2SunHotSpotVM11\\n1.4.3SunMobile—EmbeddedVMMeta—CircularVM12\\n1.4.4BEAJRockitIBMJ9VM13\\n1.4.5AzulVMBEALiquidVM14\\n1.4.6ApacheHarmonyGoogleAndroidDalvikVM14\\n1.4.7MicrosoftJVM及其他15\\n1.5展望Java技术的未来16\\n1.5.1模块化17\\n1.5.2混合语言17\\n1.5.3多核并行19\\n1.5.4进一步丰富语法20\\n1.5.564位虚拟机21\\n1.6实战：自己编译JDK22\\n1.6.1获取JDK源码22\\n1.6.2系统需求24\\n1.6.3构建编译环境25\\n1.6.4进行编译26\\n1.6.5在IDE工具中进行源码调试31\\n1.7本章小结35\\n第二部分自动内存管理机制\\n第2章Java内存区域与内存溢出异常38\\n2.1概述38\\n2.2运行时数据区域38\\n2.2.1程序计数器39\\n2.2.2Java虚拟机栈39\\n2.2.3本地方法栈40\\n2.2.4Java堆41\\n2.2.5方法区41\\n2.2.6运行时常量池42\\n2.2.7直接内存43\\n2.3HotSpot虚拟机对象探秘43\\n2.3.1对象的创建44\\n2.3.2对象的内存布局47\\n2.3.3对象的访问定位48\\n2.4实战：OutOfMemoryError异常50\\n2.4.1Java堆溢出51\\n2.4.2虚拟机栈和本地方法栈溢出53\\n2.4.3方法区和运行时常量池溢出56\\n2.4.4本机直接内存溢出59\\n2.5本章小结60\\n第3章垃圾收集器与内存分配策略61\\n3.1概述61\\n3.2对象已死吗62\\n3.2.1引用计数算法62\\n3.2.2可达性分析算法64\\n3.2.3再谈引用65\\n3.2.4生存还是死亡66\\n3.2.5回收方法区68\\n3.3垃圾收集算法69\\n3.3.1标记—清除算法69\\n3.3.2复制算法70\\n3.3.3标记—整理算法71\\n3.3.4分代收集算法72\\n3.4HotSpot的算法实现72\\n3.4.1枚举根节点72\\n3.4.2安全点73\\n3.4.3安全区域74\\n3.5垃圾收集器75\\n3.5.1Serial收集器76\\n3.5.2ParNew收集器77\\n3.5.3ParallelScavenge收集器79\\n3.5.4SerialOld收集器80\\n3.5.5ParallelOld收集器80\\n3.5.6CMS收集器81\\n3.5.7G1收集器84\\n3.5.8理解GC日志89\\n3.5.9垃圾收集器参数总结90\\n3.6内存分配与回收策略91\\n3.6.1对象优先在Eden分配91\\n3.6.2大对象直接进入老年代93\\n3.6.3长期存活的对象将进入老年代95\\n3.6.4动态对象年龄判定97\\n3.6.5空间分配担保98\\n3.7本章小结100\\n第4章虚拟机性能监控与故障处理工具101\\n4.1概述101\\n4.2JDK的命令行工具101\\n4.2.1jps：虚拟机进程状况工具104\\n4.2.2jstat：虚拟机统计信息监视工具105\\n4.2.3jinfo：Java配置信息工具106\\n4.2.4jmap：Java内存映像工具107\\n4.2.5jhat：虚拟机堆转储快照分析工具108\\n4.2.6jstack：Java堆栈跟踪工具109\\n4.2.7HSDIS：JIT生成代码反汇编111\\n4.3JDK的可视化工具114\\n4.3.1JConsole：Java监视与管理控制台115\\n4.3.2VisualVM：多合一故障处理工具122\\n4.4本章小结131\\n第5章调优案例分析与实战132\\n5.1概述132\\n5.2案例分析132\\n5.2.1高性能硬件上的程序部署策略132\\n5.2.2集群间同步导致的内存溢出135\\n5.2.3堆外内存导致的溢出错误136\\n5.2.4外部命令导致系统缓慢137\\n5.2.5服务器JVM进程崩溃138\\n5.2.6不恰当数据结构导致内存占用过大139\\n5.2.7由Windows虚拟内存导致的长时间停顿141\\n5.3实战：Eclipse运行速度调优142\\n5.3.1调优前的程序运行状态142\\n5.3.2升级JDK1.6的性能变化及兼容问题145\\n5.3.3编译时间和类加载时间的优化150\\n5.3.4调整','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,67,'2019-12-17 14:09:54',NULL,NULL,NULL),
(167,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"education\",\"orderNum\":\"1\",\"menuName\":\"图书管理\",\"params\":{},\"parentId\":1102,\"path\":\"book\",\"component\":\"book/book/index\",\"children\":[],\"createTime\":1576547360000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1103}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,65,'2019-12-17 14:22:23',NULL,NULL,NULL),
(168,'菜单管理',1,'com.dimple.project.system.controller.MenuController.add()','POST',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"新增笔记\",\"params\":{},\"parentId\":1105,\"path\":\"add\",\"component\":\"book/note/add\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,55,'2019-12-17 15:28:29',NULL,NULL,NULL),
(169,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"1\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"新增笔记\",\"params\":{},\"parentId\":1105,\"path\":\"add\",\"component\":\"book/note/add\",\"children\":[],\"createTime\":1576567709000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1107}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,103,'2019-12-17 15:28:35',NULL,NULL,NULL),
(170,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"新增笔记\",\"params\":{},\"parentId\":1105,\"path\":\"add\",\"component\":\"book/note/add\",\"children\":[],\"createTime\":1576567709000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1107}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,54,'2019-12-17 15:30:38',NULL,NULL,NULL),
(171,'菜单管理',2,'com.dimple.project.system.controller.MenuController.edit()','PUT',1,'admin','/system/menu','192.168.13.145','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"新增笔记\",\"params\":{},\"parentId\":1105,\"path\":\"book/note/add\",\"component\":\"book/note/add\",\"children\":[],\"createTime\":1576567709000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"\",\"id\":1107}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,70,'2019-12-17 15:31:40',NULL,NULL,NULL),
(172,'菜单管理',3,'com.dimple.project.system.controller.MenuController.remove()','DELETE',1,'admin','/system/menu/1107','192.168.13.145','内网IP','{id=1107}','null',0,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'id\' not found. Available parameters are [loginUsername, menuId, param1, param2]',54,'2019-12-17 15:32:18',NULL,NULL,NULL),
(173,'菜单管理',3,'com.dimple.project.system.controller.MenuController.remove()','DELETE',1,'admin','/system/menu/1107','192.168.13.145','内网IP','{id=1107}','null',0,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'id\' not found. Available parameters are [loginUsername, menuId, param1, param2]',17,'2019-12-17 15:33:48',NULL,NULL,NULL),
(174,'菜单管理',3,'com.dimple.project.system.controller.MenuController.remove()','DELETE',1,'admin','/system/menu/1107','192.168.13.145','内网IP','{id=1107}','{\"msg\":\"操作成功\",\"code\":200}',1,NULL,85,'2019-12-17 15:35:10',NULL,NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` varchar(30) NOT NULL COMMENT '角色名称',
                            `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
                            `role_sort` int(4) NOT NULL COMMENT '显示顺序',
                            `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                            `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_key`,`role_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'管理员','admin',1,'0','admin','2018-03-16 11:33:00','23','2018-03-16 11:33:00','',NULL,'管理员'),
(2,'普通角色','common',2,'0','admin','2018-03-16 11:33:00','admin','2019-11-12 19:11:06','',NULL,'普通角色'),
(3,'111','11',1,'0','admin','2019-10-23 15:45:44','admin','2019-10-23 15:46:13','admin','2019-10-23 15:46:18',NULL),
(4,'1111','111111111111111',1,'0','admin','2019-11-12 18:35:47','',NULL,'admin','2019-11-12 18:50:24','111');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values
(2,1),
(2,2),
(2,100),
(2,101),
(2,102),
(2,105),
(2,106),
(2,107),
(2,108),
(2,110),
(2,115),
(2,500),
(2,501),
(2,1001),
(2,1002),
(2,1003),
(2,1004),
(2,1005),
(2,1006),
(2,1007),
(2,1008),
(2,1009),
(2,1010),
(2,1011),
(2,1012),
(2,1013),
(2,1014),
(2,1015),
(2,1016),
(2,1026),
(2,1027),
(2,1028),
(2,1029),
(2,1030),
(2,1031),
(2,1032),
(2,1033),
(2,1034),
(2,1035),
(2,1036),
(2,1037),
(2,1038),
(2,1039),
(2,1040),
(2,1041),
(2,1042),
(2,1043),
(2,1044),
(2,1045),
(2,1049),
(2,1050),
(2,1051),
(2,1052),
(2,1053),
(2,1054),
(2,1066),
(2,1067),
(2,1068),
(2,1069),
(2,1073),
(2,1074),
(2,1075),
(2,1076),
(2,1077),
(2,1078),
(2,1079),
(2,1080),
(2,1081),
(2,1082),
(2,1083),
(2,1084),
(2,1095),
(2,1097),
(2,1098),
(3,2),
(3,109),
(3,110),
(3,111),
(3,112),
(3,1046),
(3,1047),
(3,1048),
(3,1049),
(3,1050),
(3,1051),
(3,1052),
(3,1053),
(3,1054),
(4,1066),
(4,1067),
(4,1068),
(4,1069),
(4,1073),
(4,1074),
(4,1075),
(4,1076),
(4,1077),
(4,1078),
(4,1079),
(4,1080),
(4,1081),
(4,1082),
(4,1083),
(4,1084);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `user_name` varchar(30) NOT NULL COMMENT '用户账号',
                            `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
                            `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
                            `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
                            `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
                            `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                            `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
                            `password` varchar(100) DEFAULT '' COMMENT '密码',
                            `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                            `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
                            `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                            `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`nick_name`,`user_type`,`email`,`phone`,`sex`,`avatar`,`password`,`status`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'admin','Dimple','00','bianxiaofeng@sohu.com','15555555555','0','lm_5.jpg','$2a$10$4PCH/QB72GukmOBL4Od0dOQ8iUL18UdRTiq4y8TDvJ76EZ4JIbI8O','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','admin','2019-12-12 16:22:12','',NULL,'管理员'),
(2,'test','测试','00','te1st@qq.com','15666666666','0','default.jpg','$2a$10$4PCH/QB72GukmOBL4Od0dOQ8iUL18UdRTiq4y8TDvJ76EZ4JIbI8O','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','admin','2019-11-12 19:08:13','',NULL,'测试员111'),
(3,'admin1','11','00','network@artech.com','15923652363','0','default.jpg','$2a$10$hPifjlDc.5sEbalGlp4agOmlungpzq0tUpk62X04iJvaToyFaKLse','0','',NULL,'admin','2019-10-23 15:44:49','',NULL,'admin','2019-10-23 16:52:26','111'),
(6,'222','12121','00','eew@s.com','18582872818','0','','$2a$10$E.yDpz0iIxA3KWssnmmZO./7dYK/ZCmkzQsjI1mJco7/yMDXXgYoK','0','',NULL,'admin','2019-11-12 18:59:17','',NULL,'admin','2019-11-12 19:06:06','22');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values
(1,1),
(2,2);

/*Table structure for table `tool_local_storage` */

DROP TABLE IF EXISTS `tool_local_storage`;

CREATE TABLE `tool_local_storage` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `real_name` varchar(256) DEFAULT NULL COMMENT '文件真实名称',
                                      `name` varchar(256) DEFAULT NULL COMMENT '文件名',
                                      `suffix` varchar(128) DEFAULT NULL COMMENT '后缀',
                                      `path` varchar(256) DEFAULT NULL COMMENT '路径',
                                      `type` varchar(128) DEFAULT NULL COMMENT '文件类型',
                                      `size` varchar(128) DEFAULT NULL COMMENT '大小',
                                      `create_by` varchar(128) DEFAULT NULL,
                                      `create_time` datetime DEFAULT NULL,
                                      `update_time` datetime DEFAULT NULL,
                                      `update_by` varchar(128) DEFAULT NULL,
                                      `delete_by` varchar(128) DEFAULT NULL,
                                      `delete_time` datetime DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='本地存储';

/*Data for the table `tool_local_storage` */

insert  into `tool_local_storage`(`id`,`real_name`,`name`,`suffix`,`path`,`type`,`size`,`create_by`,`create_time`,`update_time`,`update_by`,`delete_by`,`delete_time`) values
(17,'person20191212112559.png','person','png','D:\\dimple\\dimpleBlog\\file\\image\\person20191212112559.png','image','12.71KB','admin','2019-12-12 11:25:59',NULL,NULL,NULL,NULL),
(18,'person20191212112604.png','person','png','D:\\dimple\\dimpleBlog\\file\\image\\person20191212112604.png','image','12.71KB','admin','2019-12-12 11:26:04',NULL,NULL,NULL,NULL);

/*Table structure for table `tool_qi_niu_content` */

DROP TABLE IF EXISTS `tool_qi_niu_content`;

CREATE TABLE `tool_qi_niu_content` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `name` varchar(128) DEFAULT NULL COMMENT '七牛云文件名称',
                                       `bucket` varchar(128) DEFAULT NULL COMMENT '空间',
                                       `size` varchar(64) DEFAULT NULL COMMENT '文件大小',
                                       `url` varchar(128) DEFAULT NULL COMMENT '文件访问地址',
                                       `suffix` varchar(64) DEFAULT NULL COMMENT '文件后缀',
                                       `type` varchar(64) DEFAULT NULL COMMENT '类型',
                                       `create_by` varchar(128) DEFAULT NULL,
                                       `create_time` datetime DEFAULT NULL,
                                       `update_time` datetime DEFAULT NULL,
                                       `update_by` varchar(128) DEFAULT NULL,
                                       `delete_by` varchar(128) DEFAULT NULL,
                                       `delete_time` datetime DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='七牛云数据本地缓存';

/*Data for the table `tool_qi_niu_content` */

insert  into `tool_qi_niu_content`(`id`,`name`,`bucket`,`size`,`url`,`suffix`,`type`,`create_by`,`create_time`,`update_time`,`update_by`,`delete_by`,`delete_time`) values
(1,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(2,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(3,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(4,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(5,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(6,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(7,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(8,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(9,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(10,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(11,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(12,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(13,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(14,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(15,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(16,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(17,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(18,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(19,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(20,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(21,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(22,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(23,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(24,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(25,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(26,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(27,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(28,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL),
(29,'关于 hashCode 和 equals 的处理，遵循','11111','111','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9',NULL,'公开',NULL,'2019-12-11 18:03:11',NULL,NULL,NULL,NULL),
(30,'若想实现深拷贝需要重写 clone 方法实现属性对象的拷',NULL,NULL,'http://images.bianxiaofeng.com/657b07046abc1a8dd3de65ea80f37ded.jpg',NULL,'公开',NULL,'2019-12-11 18:03:13',NULL,NULL,NULL,NULL),
(31,'工具类不允许有 public 或 default 构造方法。',NULL,NULL,'http://images.bianxiaofeng.com/c395ec9d24ac314bf9ab182a1e7f05f9',NULL,'公开',NULL,'2019-12-11 18:03:14',NULL,NULL,NULL,NULL);

/*Table structure for table `tool_quartz_job` */

DROP TABLE IF EXISTS `tool_quartz_job`;

CREATE TABLE `tool_quartz_job` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
                                   `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
                                   `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
                                   `method_params` varchar(255) DEFAULT NULL COMMENT '参数',
                                   `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
                                   `status` tinyint(4) DEFAULT NULL COMMENT '状态:true表示运行,false表示暂停',
                                   `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                                   `create_by` varchar(128) DEFAULT NULL,
                                   `update_by` varchar(128) DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `delete_by` varchar(128) DEFAULT NULL,
                                   `delete_time` datetime DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务表';

/*Data for the table `tool_quartz_job` */

insert  into `tool_quartz_job`(`id`,`job_name`,`bean_name`,`method_name`,`method_params`,`cron_expression`,`status`,`remark`,`create_time`,`create_by`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'更新访客记录','visitsTask','run',NULL,'0 0 0 * * ?',0,'每日0点创建新的访客记录','2019-01-08 14:53:31',NULL,NULL,'2019-11-12 18:41:03',NULL,NULL),
(2,'测试1','testTask','run1','test','0/5 * * * * ?',0,'带参测试，多参使用json','2019-08-22 14:08:29',NULL,NULL,'2019-11-12 18:47:24',NULL,NULL),
(3,'测试','testTask','run','','0/5 * * * * ?',0,'不带参测试','2019-09-26 16:44:39',NULL,NULL,'2019-11-08 09:41:26',NULL,NULL);

/*Table structure for table `tool_quartz_log` */

DROP TABLE IF EXISTS `tool_quartz_log`;

CREATE TABLE `tool_quartz_log` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
                                   `bean_name` varchar(255) DEFAULT NULL COMMENT 'Bean的名称',
                                   `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
                                   `method_params` varchar(255) DEFAULT NULL COMMENT '参数',
                                   `cron_expression` varchar(255) DEFAULT NULL COMMENT 'corn表达式',
                                   `exception` text COMMENT '异常信息',
                                   `result` text COMMENT '返回结果值',
                                   `status` tinyint(4) DEFAULT NULL COMMENT 'true表示成功,false表示失败',
                                   `cost` bigint(20) DEFAULT NULL COMMENT '耗时',
                                   `create_time` datetime DEFAULT NULL,
                                   `create_by` varchar(128) DEFAULT NULL,
                                   `delete_time` datetime DEFAULT NULL,
                                   `delete_by` varchar(128) DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1127 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Job日志';

/*Data for the table `tool_quartz_log` */

insert  into `tool_quartz_log`(`id`,`job_name`,`bean_name`,`method_name`,`method_params`,`cron_expression`,`exception`,`result`,`status`,`cost`,`create_time`,`create_by`,`delete_time`,`delete_by`) values
(1001,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,20,'2019-11-08 16:37:05',NULL,'2019-12-12 16:26:19','admin'),
(1002,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-08 16:37:10',NULL,'2019-12-12 16:26:19','admin'),
(1003,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:37:15',NULL,'2019-12-12 16:26:19','admin'),
(1004,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-08 16:37:20',NULL,'2019-12-12 16:26:19','admin'),
(1005,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:37:25',NULL,'2019-12-12 16:26:19','admin'),
(1006,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,3,'2019-11-08 16:37:30',NULL,'2019-12-12 16:26:19','admin'),
(1007,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:37:35',NULL,'2019-12-12 16:26:19','admin'),
(1008,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:37:40',NULL,'2019-12-12 16:26:19','admin'),
(1009,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-08 16:37:45',NULL,'2019-12-12 16:26:19','admin'),
(1010,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:37:50',NULL,'2019-12-12 16:26:19','admin'),
(1011,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:37:55',NULL,'2019-12-12 16:26:19','admin'),
(1012,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,3,'2019-11-08 16:38:00',NULL,'2019-12-12 16:26:19','admin'),
(1013,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:05',NULL,'2019-12-12 16:26:19','admin'),
(1014,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:10',NULL,'2019-12-12 16:26:19','admin'),
(1015,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:15',NULL,'2019-12-12 16:26:19','admin'),
(1016,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,3,'2019-11-08 16:38:20',NULL,'2019-12-12 16:26:19','admin'),
(1017,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:25',NULL,'2019-12-12 16:26:19','admin'),
(1018,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:30',NULL,'2019-12-12 16:26:19','admin'),
(1019,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:35',NULL,'2019-12-12 16:26:19','admin'),
(1020,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:40',NULL,'2019-12-12 16:26:19','admin'),
(1021,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:45',NULL,'2019-12-12 16:26:19','admin'),
(1022,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:50',NULL,'2019-12-12 16:26:19','admin'),
(1023,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:38:55',NULL,'2019-12-12 16:26:19','admin'),
(1024,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,3,'2019-11-08 16:39:00',NULL,'2019-12-12 16:26:19','admin'),
(1025,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:39:05',NULL,'2019-12-12 16:26:19','admin'),
(1026,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:39:10',NULL,'2019-12-12 16:26:19','admin'),
(1027,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,10,'2019-11-08 16:39:15',NULL,'2019-12-12 16:26:19','admin'),
(1028,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,6,'2019-11-08 16:39:20',NULL,'2019-12-12 16:26:19','admin'),
(1029,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,449,'2019-11-08 16:39:25',NULL,'2019-12-12 16:26:19','admin'),
(1030,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,24,'2019-11-08 16:39:30',NULL,'2019-12-12 16:26:19','admin'),
(1031,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-08 16:39:35',NULL,'2019-12-12 16:26:19','admin'),
(1032,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,17,'2019-11-08 16:39:40',NULL,'2019-12-12 16:26:19','admin'),
(1033,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:39:45',NULL,'2019-12-12 16:26:19','admin'),
(1034,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:39:50',NULL,'2019-12-12 16:26:19','admin'),
(1035,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,17,'2019-11-08 16:39:55',NULL,'2019-12-12 16:26:19','admin'),
(1036,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,14,'2019-11-08 16:40:00',NULL,'2019-12-12 16:26:19','admin'),
(1037,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,15,'2019-11-08 16:40:05',NULL,'2019-12-12 16:26:19','admin'),
(1038,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,16,'2019-11-08 16:40:10',NULL,'2019-12-12 16:26:19','admin'),
(1039,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,124,'2019-11-08 16:40:16',NULL,'2019-12-12 16:26:19','admin'),
(1040,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,86,'2019-11-08 16:40:20',NULL,'2019-12-12 16:26:19','admin'),
(1041,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,6,'2019-11-08 16:40:25',NULL,'2019-12-12 16:26:19','admin'),
(1042,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:40:30',NULL,'2019-12-12 16:26:19','admin'),
(1043,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-08 16:40:35',NULL,'2019-12-12 16:26:19','admin'),
(1044,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,3,'2019-11-08 16:40:40',NULL,'2019-12-12 16:26:19','admin'),
(1045,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,10,'2019-11-08 16:40:45',NULL,'2019-12-12 16:26:19','admin'),
(1046,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-08 16:40:50',NULL,'2019-12-12 16:26:19','admin'),
(1047,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-08 16:40:55',NULL,'2019-12-12 16:26:19','admin'),
(1048,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-08 16:41:00',NULL,'2019-12-12 16:26:19','admin'),
(1049,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-08 16:41:05',NULL,'2019-12-12 16:26:19','admin'),
(1050,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-08 16:41:10',NULL,'2019-12-12 16:26:19','admin'),
(1051,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-08 16:41:15',NULL,'2019-12-12 16:26:19','admin'),
(1052,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-08 16:41:20',NULL,'2019-12-12 16:26:19','admin'),
(1053,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:41:05',NULL,'2019-12-12 16:26:19','admin'),
(1054,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:41:10',NULL,'2019-12-12 16:26:19','admin'),
(1055,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:41:15',NULL,'2019-12-12 16:26:19','admin'),
(1056,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:41:20',NULL,'2019-12-12 16:26:19','admin'),
(1057,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:41:25',NULL,'2019-12-12 16:26:19','admin'),
(1058,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:41:30',NULL,'2019-12-12 16:26:19','admin'),
(1059,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:41:35',NULL,'2019-12-12 16:26:19','admin'),
(1060,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:41:40',NULL,'2019-12-12 16:26:19','admin'),
(1061,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:41:45',NULL,'2019-12-12 16:26:19','admin'),
(1062,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:41:50',NULL,'2019-12-12 16:26:19','admin'),
(1063,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,12,'2019-11-12 18:41:55',NULL,'2019-12-12 16:26:19','admin'),
(1064,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:00',NULL,'2019-12-12 16:26:19','admin'),
(1065,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:05',NULL,'2019-12-12 16:26:19','admin'),
(1066,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:10',NULL,'2019-12-12 16:26:19','admin'),
(1067,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,212,'2019-11-12 18:42:15',NULL,'2019-12-12 16:26:19','admin'),
(1068,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:20',NULL,'2019-12-12 16:26:19','admin'),
(1069,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:25',NULL,'2019-12-12 16:26:19','admin'),
(1070,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:42:30',NULL,'2019-12-12 16:26:19','admin'),
(1071,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:42:35',NULL,'2019-12-12 16:26:19','admin'),
(1072,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:40',NULL,'2019-12-12 16:26:19','admin'),
(1073,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:42:45',NULL,'2019-12-12 16:26:19','admin'),
(1074,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:42:50',NULL,'2019-12-12 16:26:19','admin'),
(1075,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:42:55',NULL,'2019-12-12 16:26:19','admin'),
(1076,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,6,'2019-11-12 18:43:00',NULL,'2019-12-12 16:26:19','admin'),
(1077,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:05',NULL,'2019-12-12 16:26:19','admin'),
(1078,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:43:10',NULL,'2019-12-12 16:26:19','admin'),
(1079,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:43:15',NULL,'2019-12-12 16:26:19','admin'),
(1080,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:20',NULL,'2019-12-12 16:26:19','admin'),
(1081,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:25',NULL,'2019-12-12 16:26:19','admin'),
(1082,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:30',NULL,'2019-12-12 16:26:19','admin'),
(1083,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:35',NULL,'2019-12-12 16:26:19','admin'),
(1084,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:40',NULL,'2019-12-12 16:26:19','admin'),
(1085,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:45',NULL,'2019-12-12 16:26:19','admin'),
(1086,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:43:50',NULL,'2019-12-12 16:26:19','admin'),
(1087,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,3,'2019-11-12 18:43:55',NULL,'2019-12-12 16:26:19','admin'),
(1088,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:44:00',NULL,'2019-12-12 16:26:19','admin'),
(1089,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:05',NULL,'2019-12-12 16:26:19','admin'),
(1090,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:10',NULL,'2019-12-12 16:26:19','admin'),
(1091,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:15',NULL,'2019-12-12 16:26:19','admin'),
(1092,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:20',NULL,'2019-12-12 16:26:19','admin'),
(1093,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:44:25',NULL,'2019-12-12 16:26:19','admin'),
(1094,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:30',NULL,'2019-12-12 16:26:19','admin'),
(1095,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:35',NULL,'2019-12-12 16:26:19','admin'),
(1096,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:40',NULL,'2019-12-12 16:26:19','admin'),
(1097,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,55,'2019-11-12 18:44:45',NULL,'2019-12-12 16:26:19','admin'),
(1098,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:44:50',NULL,'2019-12-12 16:26:19','admin'),
(1099,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:44:55',NULL,'2019-12-12 16:26:19','admin'),
(1100,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,18,'2019-11-12 18:45:00',NULL,'2019-12-12 16:26:19','admin'),
(1101,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:45:05',NULL,'2019-12-12 16:26:19','admin'),
(1102,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:45:10',NULL,'2019-12-12 16:26:19','admin'),
(1103,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:45:15',NULL,'2019-12-12 16:26:19','admin'),
(1104,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:45:20',NULL,'2019-12-12 16:26:19','admin'),
(1105,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:45:25',NULL,'2019-12-12 16:26:19','admin'),
(1106,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,NULL,'2019-11-12 18:45:30',NULL,'2019-12-12 16:26:19','admin'),
(1107,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,13,'2019-11-12 18:45:35',NULL,'2019-12-12 16:26:19','admin'),
(1108,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,4,'2019-11-12 18:45:55',NULL,'2019-12-12 16:26:19','admin'),
(1109,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:00',NULL,'2019-12-12 16:26:19','admin'),
(1110,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:05',NULL,'2019-12-12 16:26:19','admin'),
(1111,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:10',NULL,'2019-12-12 16:26:19','admin'),
(1112,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:46:15',NULL,'2019-12-12 16:26:19','admin'),
(1113,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:20',NULL,'2019-12-12 16:26:19','admin'),
(1114,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:46:25',NULL,'2019-12-12 16:26:19','admin'),
(1115,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:30',NULL,'2019-12-12 16:26:19','admin'),
(1116,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:35',NULL,'2019-12-12 16:26:19','admin'),
(1117,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:40',NULL,'2019-12-12 16:26:19','admin'),
(1118,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:45',NULL,'2019-12-12 16:26:19','admin'),
(1119,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:46:50',NULL,'2019-12-12 16:26:19','admin'),
(1120,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,29,'2019-11-12 18:46:55',NULL,'2019-12-12 16:26:19','admin'),
(1121,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:47:00',NULL,'2019-12-12 16:26:19','admin'),
(1122,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:47:05',NULL,'2019-12-12 16:26:19','admin'),
(1123,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,2,'2019-11-12 18:47:10',NULL,'2019-12-12 16:26:19','admin'),
(1124,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:47:15',NULL,'2019-12-12 16:26:19','admin'),
(1125,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,NULL,1,1,'2019-11-12 18:47:20',NULL,'2019-12-12 16:26:19','admin'),
(1126,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,'这是返回值',1,3812,'2019-12-03 12:14:40',NULL,'2019-12-12 16:26:19','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
