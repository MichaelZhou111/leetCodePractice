package com.jd.st.JUCLearn.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author ZhouZhiping
 * @date 2019/11/29 13:42
 * @Email zhouzhiping(a)jd.com
 *
 * https://juejin.im/post/5adbf8226fb9a07aac240a67
 */

public class AnyOfDemo {

    /**
     * 我们使用thenCompose()和 thenCombine()把两个CompletableFuture组合在一起。
     * 现在如果你想组合任意数量的CompletableFuture，应该怎么做？我们可以使用以下两个方法组合任意数量的CompletableFuture。
     static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
     static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)

     作者：yekeqiang
     链接：https://juejin.im/post/5adbf8226fb9a07aac240a67
     来源：掘金
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * 假设你想下载一个网站的100个不同的页面。你可以串行的做这个操作，但是这非常消耗时间。因此你想写一个函数，
     * 传入一个页面链接，返回一个CompletableFuture，异步的下载页面内容。
     */
    CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // Code to download and return the web page's content
            Long time2Sleep = 0L;
            try {
                time2Sleep= RandomUtils.nextLong(100,2000);
                Thread.sleep(time2Sleep);
                System.out.println(Thread.currentThread().getName()+"\t running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return pageLink+"\t"+String.valueOf(time2Sleep);
        });
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AnyOfDemo anyOfDemo = new AnyOfDemo();

        // A list of 10 web page links
        List<String> webPageLinks = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> anyOfDemo.downloadWebPage(webPageLink))
                .collect(Collectors.toList());
        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
        );

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });

        System.out.println(allPageContentsFuture.get());


    }
}
