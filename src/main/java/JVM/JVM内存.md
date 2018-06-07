
[](http://images.cnitblog.com/i/485345/201405/300854081661499.jpg)

* VM栈（VM Stack）
    用于描述JAVA方法执行时的内存模型，每个方法执行时会创建一个stack frame用于存储局部变量表（基本数据类型，对象引用【reference类型】，操作数栈，动态链接，方法出入口等信息；
    线程请求栈深度大于虚拟机允许深度时，抛出StackOverFlow异常；
    JAVA虚拟机动态允许动态扩展栈深度，当拓展无法满足时，抛出OutOfMemoryError异常。
* native方法栈（native method stack）
    给虚拟机native方法服务，Sun HotSpot直接把VM Stack与之合二为一。
* 堆（heap）
    存放对象实例；
    GC的主要区域，现代虚拟机采用分区收集算法；
    Eden，From Survivor,To Survivor等；
    无法拓展时抛出抛出OutOfMemoryError异常。
* 方法区(method area)
    HotSpot虚拟机上成为Permanent Generation；
    存储虚拟机加载的类信息，常量，静态变量，即时编译后的代码等数据；
    无法拓展时抛出抛出OutOfMemoryError异常。
    运行时常量池：方法区一部分。
* 程序计数器(program counter register)
    线程所执行的字节码的行号指示器；
    如果方法为JAVA方法，计数器记录当前执行的虚拟机字节码指令地址，如果是native方法则为Undefined；
    没有规定OutOfMemoryError!
  
    