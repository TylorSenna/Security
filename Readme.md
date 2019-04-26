# 计算机网络安全

# Caesar加密 
- 加密：循环左移25次
- 解密：循环右移25次，通过SpellCorrect来判断揭秘出的26个数据是否拼写正确，拼写正确的那个就是正确的结果，即解密成功（我这里只是判断前两个单词拼写正确，一般是够用了）

# DES加密
### 加密
- IP置换、
- 16轮加密L（），轮函数F_function();
- 子密钥生成
### 解密
- 把子密钥倒过来用，即第十六轮加密用的密钥在解密第一轮的时候使用

# RSA加密（Java  BigInteger）
### 流程
- 选择 p、q两个超级大的质数 ，都是1024位。
- 令n = p * q。取 φ(n)  =(p-1) * (q-1)。
- gcd(φ(n),e)=1，取 e ∈ 1 < e < φ(n)  ，( n , e )作为公钥对，正式环境中取65537。可以打开任意一个被认证过的https证书，都可以看到。
- 令 ed mod φ(n)  = 1，计算d，( n , d ) 作为私钥对。 计算d可以利用扩展欧几里的算法进行计算。
- 密文 = 明文 ^ e mod n，明文 = 密文 ^ d mod n。利用蒙哥马利方法进行计算，也叫反复平方法。
- BigInteger 和 String 转换

### RSA String转BigInteger解决方案
对RSA进行分段、段长度等于n/8-11 byte。
- 加密: 对于加密前，通过Ascll码把String转化为biginteger，在前面加上999来作为起始符号，因为085转为字符串0就会消失。
- 解密: 同样解密完的数据直接把前三个byte截去，把后面的数据通过ascll码转回String。

在加密后，数据直接加上标识符“A”来拼接起来成为字符串发送至接收端，接收端通过“A”来分段解析数据。（因为数据可能出现785这种数据，而通过char转字符串必须0-128，所以不能通过ascll码来转成String，而是直接把数据加上A标识符拼接起来）
### DES String转BigSet解决方案
DES：
- 加密：
加密前：String转Bitset，由于要考虑到中文，所以也是要在开始用base64编码一下，用bas64String_to_bitset，加密完要把bitset转化为base64String，因为如果不转化的话，bitset中的某个8bit数据有可能是1xxxxxxx这种负数，导致用ascll码转换string出错，所以用bitset_to_base64string把bitset转化为byte[]再用base64编码成为密文传给接收端。
- 解密：
解密前，同样先用bas64String_to_bitset，对bitset进行解密，之后用bitset_to_base64string转为原文，此时要再通过一次base64解码，因为在加密前为了保证中文得到处理就已经进行了一次base64编码，所以要对应起来解码。得到原始明文。