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
###### 2.在FragmentPagerAdapter中 每次在instantiateItem中调用getItemId()的时候，都会是不同的id。适配器发现找不到之前的碎片，就会重新调用getItem来新建碎片。这个方法是适配器用来组装tag的一部分。只要改变了它，也就改变了tag。
###### 3.每次调用notifyDataSetChanged()时，都会激活getItemPosition(Object object)方法，该方法会遍历viewpager的所有缓存的item，为每个item返回一个状态值（POSITION_NONE/POSITION_UNCHANGED），如果是none，那么该item会被destroyItem(ViewGroup container, int position, Object object)方法remove掉，然后重新加载，如果是unchanged，就不会重新加载，默认是unchanged，所以如果我们不重写getItemPosition(Object object)，就无法看到刷新效果。

##### 基于以上得到思路:    
##### 1.为每一个Fragment保存一个对应的key（使用SparseArray<TestFragment>）    
           mTestFragments = new SparseArray<>();
           mTestFragments.put(key++,TestFragment.newInstance("第一"));
           mTestFragments.put(key++,TestFragment.newInstance("第二"));
           mTestFragments.put(key++,TestFragment.newInstance("第三"));
           mTestFragments.put(key++,TestFragment.newInstance("第四"));
           mTestFragments.put(key++,TestFragment.newInstance("第五"));
##### 2.重写getItemId(int position) 返回对应的key 
            @Override
            public long getItemId(int position) {
                Log.d(TAG, "position: "+position+"  getItemId:"+mTestFragments.keyAt(position));
                return mTestFragments.keyAt(position);
            }
            
##### 3.重写getItem(int position)

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: "+position);
        TestFragment testFragment=mTestFragments.valueAt(position);
        testFragment.tag=mTestFragments.keyAt(position);
        return testFragment;
    }

    
##### 4.重写getItemPosition 否刷新页面    
    @Override
    public int getItemPosition(Object object) {
            return POSITION_NONE;
      
    }
    
##### 5.新增和删除调用

               //删除
                mTestFragments.removeAt(mCurPos);
                mPagerAdapter.notifyDataSetChanged();
                
                //新增
                mTestFragments.put(key++,TestFragment.newInstance("第"+key));
                mPagerAdapter.notifyDataSetChanged();

##### 经过以上处理后，新增和删除都只会影响当前Fragment的生命周期,其他已经缓存的Fragment都不会重新创建，而是直接在FragmentManager中读取缓存复用

![image](https://github.com/bux-git/ViewPagerFragmentAD/raw/master/viewpage_fragment.gif)