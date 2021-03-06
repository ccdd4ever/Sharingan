# 线程入门
## 线程风险
* 安全性问题
* 活跃性问题（死锁，饥饿，活锁等）
* 性能问题（上下文切换，CPU调度，内存总线同步流量）
* 相关概念：原子性，竞态条件(通常因check-then-act造成），复合操作，内置锁，重入锁

## 如何确保线程安全
* 加锁能确保操作的原子性以及内存可见性(memory-visibility)
* volatile提供一种比加锁轻量级的同步机制，声明volatile后编译器与运行时不会把该变量上的操作与其他内存操作一起重排序，也不会被缓存在寄存器或其他处理器不可见的地方，满足以下条件时才使用volatile
    1 对变量的写入操作不依赖变量当前的值，或者确保只有单个线程修改（不适用与多线程中自增的值）
    2 该变量不会与其他状态变量一起纳入不变性条件中
    3 在访问变量时不需要加锁·
* 最低安全性:线程未同步可能读到失效数据，但至少是之前某个线程设置的值（out-of-thin-air-safety)适用于大多数变量，_对于
64位数值变量可能拆分为两个32位的原子操作_，除非用volatile标记或者锁保护



