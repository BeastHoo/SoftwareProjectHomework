# SoftwareProjectHomework
CTGU19级计算机科学与技术软件工程作业用

  ***此程序基于JAVA语言，运行是需要JRE支持的***  
__我在完成这份作业时有过对优化的一些思路，虽然没有付诸行动，但是还是做一下记录  
首先，关于各个频率的排序问题，可以通过维护大根堆来完成，特别是在遇到大文件时，大根堆的优势更加明显  
其次，在遍历统计某一目录下的所有文件及其子目录下的所有文件时，我考虑过使用B+树做维护(捂脸)，不过好像性能提升并不会有提升，还比递归更加麻烦。  
这个项目有一个功能有很大的缺陷，就是动词的归一化那一步。我的解决方案是将一个不规则动词形态表加载到map里面，然后去匹配，这样做只能进行不规则动词的归一化  
而且空间和时间复杂度都挺高的，老师说可以使用NLP，可是我只是一个小小本科生__



## 第一步：
   输出单个文件中的前 N 个最常出现的英语单词。
作用：一个用于统计文本文件中的英语单词出现频率的控制台程序
单词：以英文字母开头，由英文字母和字母数字符号组成的字符串视为一个单词。单词以分隔符分割且不区分大小写。在输出时，所有单词都用小写字符表示。
英文字母：A-Z，a-z
字母数字符号：A-Z，a-z，0-9
分割符：空格,非字母数字符号 例：good123是一个单词，123good不是一个单词。good，Good和GOOD是同一个单词
功能列表：

### 功能1： wf.exe -f
  输出文件中所有不重复的单词，按照出现次数由多到少排列，出现次数同样多的，以字典序排列。

### 功能2： wf.exe -d 指定文件目录，对目录下每一个文件执行 wf.exe -f 的操作。
  wf.exe -d -s 同上， 但是会递归遍历目录下的所有子目录。

### 功能3： 支持 -n 参数，输出出现次数最多的前 n 个单词， 例如， -n 10 就是输出最常出现单词的前 10 名。 当没有指明数量的时候，我们默认列出所有单词的频率。
  现在我们这个程序已经有一点复杂度了， 我们要构建一些基本的测试用例来保证程序的基本功能不会在不断的扩展中出问题。 请看 《构建之法》【回归测试】的内容，构建一些测试来保证基本功能的正确性。

## 第二步:
  支持 stop words
我们从第一步的结果看出，在一本小说里， 频率出现最高的单词一般都是 “a”, “it”, “the”, “and”, “this”, 这些词， 我们并不感兴趣. 我们可以做一个 stop word 文件 （停词表）， 在统计词汇的时候，跳过这些词。 我们把这个文件叫 “stopwords.txt” file.

### 功能 4： 支持新的命令行参数, 例如： wf.exe -x -f
  在这一步我们要增加什么回归测试呢？

## 第三步:
  我们想看看常用的短语是什么， 怎么办呢？
先定义短语：”两个或多个英语单词， 它们之间只有空格分隔”. 请看下面的例子：
　　hello world //这是一个短语
　　hello, world //这不是一个短语

### 功能 5： 支持新的命令行参数 -p
　　参数 说明要输出多少个词的短语，并按照出现频率排列。同一频率的词组， 按照字典序来排列。

  在这一步我们要增加什么回归测试呢？

## 第四步：
  把动词形态都统一之后再计数。
我们想找到常用的单词和短语，但是发现英语动词经常有时态和语态的变化，导致同一个词，同一个短语却被认为是不同的。 怎么解决这个问题呢？
假设我们有这样一个文本文件，这个文件的每一行都是这样构成：
　　动词原型 动词变形1 动词变形2…
词之间用空格分开。

e.g. 动词 TAKE 有下面的各种变形
　　take takes took taken taking
我们希望在实现上面的各种功能的时候，有一个选项， 就是把动词的各种变形都归为它的原型来统计。

### 功能 6： 支持动词形态的归一化，参数为 -v
　　wf.exe -v 其中 是纪录动词形态的文本文件。
实现这些功能， 分析程序的效能 。
