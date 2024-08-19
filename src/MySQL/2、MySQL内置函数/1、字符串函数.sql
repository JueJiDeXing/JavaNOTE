/*
concat(...s)        拼接字符串
lower(s),upper(s)   转小写,转大写
length(s)           获取字符串长度

substring(s,idx,n)  从idx开始截取n个字符 # 注意:SQL语言的索引从1开始
left(s,n)           截取左边n个字符
right(s,n)          截取右边n个字符

replace(s,old,new)  替换所有old字符串
insert(s,idx,n,new) 替换[idx,idx+n)字符串

instr(s,sub)        返回sub在s中的索引位置,未找到为0
field(s,...sn)      返回s在列表sn的位置(第一个),未找到为0
find_in_set(s,str)  返回s在列表str的位置(str为逗号分隔的字符串),未找到为0

repeat(s,n)         重复s字符串n次
space(n)            返回n个空格

strcmp(s1,s2)       比较两个字符串大小

lpad(s,length,pad)  以pad在左侧填充字符串,使字符串长度达到length
rpad(s,length,pad)  以pad在右侧填充字符串,使字符串长度达到length

ltrim(s)            去除左侧空格
rtrim(s)            去除右侧空格
trim(s)             去除头尾空格
trim(s1 from s2)    去除s2头尾的s1
trim(leading|trailing|both pad from s) 去除指定pad的字符

ascii(s)             返回字符s的第一个字符的ASCII码值
charset(s)           返回字符串s的字符编码
convert(s using char_code)     将字符串s字符编码改为char_code
例:
update user set id=lpad(id,5,'0'); # 以0填充id,补足5位
# 注:隐式转换,字符函数也可作用于数值类型上



*/
