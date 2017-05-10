# ViewPagerFragmentAD
Android Viewpager+fragment  动态添加删除Fragment  复用FragmentManager中缓存的Fragment      

#### 方案1：    

[动态更新Viewpager+fragment](http://stackoverflow.com/questions/10396321/remove-fragment-page-from-viewpager-in-android)     
这种方法可以实现动态新增删除,但是有一个缺点 会重建所有的Fragment 造成性能浪费，    
比如目前项目中Fragment中是有网络数据处理的 重新创建Fragment的话将重复调用服务器接口，    
对于用户体验 服务器访问等都造成了 不良后果 遂优化如方案2    

#### 方案2：       
[ViewPager 详解（二）---详解四大函数](http://blog.csdn.net/harvic880925/article/details/38487149)    
[动态更新Viewpager+fragment](http://stackoverflow.com/questions/10396321/remove-fragment-page-from-viewpager-in-android)    

根据方案1与以上资料,可知：    
###### 1.每个滑动页面都对应一个Key，而且这个Key值是用来唯一追踪这个页面的，也就是说每个滑动页面都与一个唯一的Key一一对应     
###### 2.在FragmentPagerAdapter中 getItemId()   每次在instantiateItem中调用这个的时候，都会是不同的id。适配器发现找不到之前的碎片，就会重新调用getItem来新建碎片。这个方法是适配器用来组装tag的一部分。只要改变了它，也就改变了tag。
   也就是说我们只需要在
