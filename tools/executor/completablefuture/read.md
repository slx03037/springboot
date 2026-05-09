Future是什么?
Java 1.5中引入Callable解决多线程执行无返回值的问题。
Future是为了配合Callable/Runnable而产生的。简单来讲，我们可以通过future来对任务查询、取消、执行结果的获取，是调用方与异步执行方之间沟通的桥梁。
FutureTask实现了RunnableFuture接口，同时具有Runnable、Future的能力，即既可以作为Future得到Callable的返回值，又可以作为一个Runnable。
CompletableFuture实现了Futrue接口。
Future是Java5新加的一个接口，它提供了一种异步并行计算的功能。如果主线程需要执行一个很耗时的计算任务，我们可以将这个任务通过Future放到异步线程中去执行。主线程继续处理其他任务，处理完成后，再通过Future获取计算结果。
Future可以在连续流程中满足数据驱动的并发需求，既获得了并发执行的性能提升，又不失连续流程的简洁优雅。

CompletableFuture创建异步任务，一般有supplyAsync和runAsync两个方法：
supplyAsync执行CompletableFuture任务，支持返回值。
runAsync执行CompletableFuture任务，没有返回值。

supplyAsync方法：
//使用默认内置线程池ForkJoinPool.commonPool()，根据supplier构建执行任务
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
return asyncSupplyStage(ASYNC_POOL, supplier);
}
//自定义线程，根据supplier构建执行任务
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,
Executor executor) {
return asyncSupplyStage(screenExecutor(executor), supplier);
}

runAsync方法:
//使用默认内置线程池ForkJoinPool.commonPool()，根据runnable构建执行任务
public static <U> CompletableFuture<U> completedFuture(U value) {
return new CompletableFuture<U>((value == null) ? NIL : value);
}
//自定义线程，根据runnable构建执行任务
public static CompletableFuture<Void> runAsync(Runnable runnable,
Executor executor) {
return asyncRunStage(screenExecutor(executor), runnable);
}

异步任务回调

thenRun / thenRunAsync
CompletableFuture的thenRun方法，通俗点讲就是，做完第一个任务后，再做第二个任务。
某个任务执行完成后，执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
public CompletableFuture<Void> thenRun(Runnable action);
public CompletableFuture<Void> thenRunAsync(Runnable action);
thenRun / thenRunAsync的区别:
private static final Executor asyncPool = useCommonPool ?
ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();

public CompletableFuture<Void> thenRun(Runnable action) {
return uniRunStage(null, action);
}

public CompletableFuture<Void> thenRunAsync(Runnable action) {
return uniRunStage(asyncPool, action);
}
如果你执行第一个任务的时候，传入了一个自定义线程池：

调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。
调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池， 第二个任务使用的是ForkJoin线程池。

thenAccept / thenAcceptAsync
CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的。
public CompletableFuture<Void> thenAccept(Consumer<? super T> action) {
return uniAcceptStage(null, action);
}
public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action) {
return uniAcceptStage(defaultExecutor(), action);
}
public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action,
Executor executor) {
return uniAcceptStage(screenExecutor(executor), action);
}

thenApply / thenApplyAsync
CompletableFuture的thenApply方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，并且回调方法是有返回值的。
public CompletableFuture<Void> thenAccept(Consumer<? super T> action) {
return uniAcceptStage(null, action);
}
public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action) {
return uniAcceptStage(defaultExecutor(), action);
}
public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action,
Executor executor) {
return uniAcceptStage(screenExecutor(executor), action);
}

thenCombine / thenCombineAsync
thenCombine方法接收两个参数：other和fn。other参数是另一个CompletionStage对象，表示要进行组合操作的第二个阶段；
fn参数是一个BiFunction函数式接口，定义了如何处理两个阶段的结果并生成最终的结果。

在thenCombine方法的实现中，调用了biApplyStage方法，它是一个私有方法。这个方法的作用是创建一个新的CompletableFuture对象，
并将当前的CompletableFuture对象和other对象传递给它，以及fn函数式接口。
public <U,V> CompletableFuture<V> thenCombine(
CompletionStage<? extends U> other,
BiFunction<? super T,? super U,? extends V> fn) {
return biApplyStage(null, other, fn);
}
public <U,V> CompletableFuture<V> thenCombineAsync(
CompletionStage<? extends U> other,
BiFunction<? super T,? super U,? extends V> fn) {
return biApplyStage(defaultExecutor(), other, fn);
}
public <U,V> CompletableFuture<V> thenCombineAsync(
CompletionStage<? extends U> other,
BiFunction<? super T,? super U,? extends V> fn, Executor executor) {
return biApplyStage(screenExecutor(executor), other, fn);

exceptionally
CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
public CompletableFuture<T> exceptionally(
Function<Throwable, ? extends T> fn) {
return uniExceptionallyStage(fn);
}
}

whenComplete
CompletableFuture的whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值；并且whenComplete方法返回的CompletableFuture的result是上个任务的结果。
public CompletableFuture<T> whenComplete(
BiConsumer<? super T, ? super Throwable> action) {
return uniWhenCompleteStage(null, action);
}

handle
public <U> CompletableFuture<U> handle(
BiFunction<? super T, Throwable, ? extends U> fn) {
return uniHandleStage(null, fn);
}
public <U> CompletableFuture<U> handleAsync(
BiFunction<? super T, Throwable, ? extends U> fn) {
return uniHandleStage(defaultExecutor(), fn);
}
public <U> CompletableFuture<U> handleAsync(
BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
return uniHandleStage(screenExecutor(executor), fn);
}

多个任务组合处理
AND组合关系
thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来，只有这两个都正常执行完了，才会执行某个任务。
thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值。
thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值。
runAfterBoth 不会把执行结果当做方法入参，且没有返回值。

OR组合关系

applyToEither / acceptEither / runAfterEither 都表示：将两个CompletableFuture组合起来，只要其中一个执行完了,就会执行某个任务。
applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值。
acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值。
runAfterEither：不会把执行结果当做方法入参，且没有返回值。

AllOf
所有任务都执行完成后，才执行 allOf返回的CompletableFuture。如果任意一个任务异常，allOf的CompletableFuture，执行get方法，会抛出异常。

AnyOf
任意一个任务执行完，就执行anyOf返回的CompletableFuture。如果执行的任务异常，anyOf的CompletableFuture，执行get方法，会抛出异常。

thenCompose
thenCompose方法会在某个任务执行完成后，将该任务的执行结果,作为方法入参,去执行指定的方法。该方法会返回一个新的CompletableFuture实例。
如果该CompletableFuture实例的result不为null，则返回一个基于该result新的CompletableFuture实例。
如果该CompletableFuture实例为null，然后就执行这个新任务。


Future需要获取返回值，才能获取到异常信息。如果不加 get()/join()方法，看不到异常信息。小伙伴们使用的时候，注意一下哈，考虑是否加try…catch…或者使用exceptionally方法。

CompletableFuture的get()方法是阻塞的

CompletableFuture的get()方法是阻塞的，如果使用它来获取异步调用的返回值，需要添加超时时间

默认线程池的注意点

CompletableFuture代码中又使用了默认的线程池，处理的线程个数是电脑CPU核数-1。在大量请求过来的时候，处理逻辑复杂的话，响应会很慢。一般建议使用自定义线程池，优化线程池配置参数。

自定义线程池时，注意饱和策略

CompletableFuture的get()方法是阻塞的，我们一般建议使用future.get(3, TimeUnit.SECONDS)。并且一般建议使用自定义线程池。但是如果线程池拒绝策略是DiscardPolicy或者DiscardOldestPolicy，当线程池饱和时，会直接丢弃任务，不会抛弃异常。

因此建议，CompletableFuture线程池策略最好使用AbortPolicy，然后耗时的异步线程，做好线程池隔离