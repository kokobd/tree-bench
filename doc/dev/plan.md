# Development Plan

## Requirements
- 能够读取文件，利用不同的词典实现来统计单词信息
- 必须显示的统计信息：频率最高的前30个单词及其频率、单词总数
- 必须显示的跑分信息：计算前述统计信息时，元素的**比较次数**与**总耗时**，
  树的高度、最深层次的数据元素
- 用户的主要操作：
  1. 添加文件（这会把文件内容添加到所有词典）
  2. 清空全部二叉树
- 单词(word)的定义：
  > A word is defined as a sequence of letters, together with
  apostrophes (’) and hyphens (-), provided that the apostrophe or 
  hyphen is both immediately preceded and followed by a letter. 
  Uppercase and lowercase letters should be regarded as the same 
  (by translating all letters into either uppercase or lowercase,
  as you prefer). A word is to be truncated to its first 20 
  characters (that is, only 20 characters are to be stored in the 
  data structure) but words longer than 20 characters may appear 
  in the text. Non-alphabetic characters (such as digits, blanks, 
  punctuation marks, control characters) may appear in the text file. 
  The appearance of any of these terminates a word, and the next 
  word begins only when a letter appears.