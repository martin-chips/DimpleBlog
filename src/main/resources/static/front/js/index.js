var pageNum = 0;
$(function () {
    loadNewestBlog();
    // loadClickBlog();
    //鼠标滚动到页面最底部加载数据
    var documentHeight = [0];
    $(window).bind("scroll", function () {
        if ($(document).scrollTop() + $(window).height() > $(document).height() - 1) {
            documentHeight.push($(document).height())
            if (documentHeight[documentHeight.length - 1] > documentHeight[documentHeight.length - 2]) {
                documentHeight[documentHeight.length - 2] = documentHeight[documentHeight.length - 2] + documentHeight[documentHeight.length - 1];
                // $(".res-more").css("display","none");
                // self.getShareFiles();
                loadNewestBlog();
            }
        } else {
            // $(".res-more").css("display","block");
        }
    })
});

/**
 * 加载点击排行的博客
 */
function loadClickBlog() {
    $.ajax({
        type: "get",
        url: "/public/api/front/clickBlog",
        success: function (result) {
            console.log(result);
            if (result.code == 200) {
                generateClickBlog(result.data);
            }
        }
    });
}


/**
 * 加载最新的博文
 */
function loadNewestBlog() {
    $.ajax({
        type: "get",
        url: "/public/api/front/newestBlog",
        data: {
            pageNum: pageNum
        },
        success: function (result) {
            if (result.code == 200) {
                generateNewestBlog(result.data);
                pageNum++;
            }
        }
    });

}

// /**
//  *
//  * @param data
//  */
// function generateClickBlog(data) {
//     var ranking = $(".paihang");
//     var section = $("<section></section>");
//     section.addClass("topnews imgscale");
//     var sectionHref = $("<a></a>");
//     sectionHref.attr("href", "/view/" + data[0].blogId);
//     var sectionHrefImage = $("<img>");
//     sectionHrefImage.attr("src", data[0].headerUrl);
//     var span = $("<span></span>");
//     span.text(data[0].title);
//
//     //拼接
//     sectionHrefImage.append(span);
//     sectionHref.append(sectionHrefImage);
//     section.append(sectionHref);
//     ranking.append(section);
//     for (var i = 0; i < data.length; i++) {
//
//     }
//
//
// }


/**
 *
 * @param data
 */
function generateNewestBlog(data) {
    var node = $("#blogNewest");
    for (var i = 0; i < data.length; i++) {
        var item = data[i];//获取data
        var li = $("<li></li>");
        //第一部分 标题
        var blogTitle = $("<h3></h3>");
        blogTitle.addClass('blogtitle');//添加class
        var blogTitleHref = $("<a target='_blank'></a>");
        blogTitleHref.attr("href", "/view/" + item.blogId);//设置点击链接
        blogTitleHref.text(item.title);//设置标题文字
        //拼接
        blogTitle.append(blogTitleHref);
        li.append(blogTitle);

        //第二部分  图片展示
        var blogPic = $("<span class='blogpic imgscale'></span>");
        var blogPicI = $("<i></i>");
        var blogPicIHref = $("<a></a>");
        blogPicIHref.text(item.categoryName);//设置图片上显示的文字
        var blogPicHref = $("<a href='' title=''></a>");//设置图片的链接
        var blogPicImg = $("<img>");
        blogPicImg.attr("src", item.headUrl);//设置图片地址
        blogPicI.append(blogPicIHref);
        blogPic.append(blogPicI);//拼接I标签
        blogPicHref.append(blogPicImg);
        blogPic.append(blogPicHref);//拼接a标签
        li.append(blogPic);

        //第三部分 博客摘要
        var blogContent = $("<p></p>");
        blogContent.addClass("blogtext");
        blogContent.text(item.summary);//设置摘要内容
        li.append(blogContent);

        //第四部分 博客的信息
        var blogInfo = $("<p ></p>");
        blogInfo.addClass("bloginfo");//添加class
        var avator = $("<i ></i>");
        avator.addClass("avatar");//添加class
        var avatorImg = $("<img>");
        avatorImg.attr("src", "/front/images/avatar.jpg");//设置图片地址
        avator.append(avatorImg);//拼接
        //博客作者
        var author = $("<span></span>");
        author.text(item.author);
        //博客日期
        var time = $("<span></span>");
        time.text(item.time);
        //标签
        var category = $("<span></span>");
        category.text("【");
        var categoryHref = $("<a></a>");
        categoryHref.attr("href", "");//未设置
        categoryHref.text(item.categoryName);
        category.append(categoryHref);
        category.append("】");
        blogInfo.append(avator);
        blogInfo.append(author);
        blogInfo.append(time);
        blogInfo.append(category);
        li.append(blogInfo);
        //阅读更多
        var readmore = $("<a class='viewmore'>阅读更多</a>");
        li.append(readmore);
        node.append(li);
    }

}

