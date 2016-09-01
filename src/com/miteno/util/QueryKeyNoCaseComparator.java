/*
 * 鏂? 浠? 鍚?:  QueryKeyNoCaseComparator.java
 * 鐗?    鏉?:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 鎻?    杩?:  <鎻忚堪>
 * 淇? 鏀? 浜?:  sWX166323
 * 淇敼鏃堕棿:  2014-10-27
 * 璺熻釜鍗曞彿:  <璺熻釜鍗曞彿>
 * 淇敼鍗曞彿:  <淇敼鍗曞彿>
 * 淇敼鍐呭:  <淇敼鍐呭>
 */
package com.miteno.util;

import java.util.Comparator;

/**
 * <涓?鍙ヨ瘽鍔熻兘绠?杩?>
 * <鍔熻兘璇︾粏鎻忚堪>
 * 
 * @author  sWX166323
 * @version  [鐗堟湰鍙?, 2014-10-27]
 * @see  [鐩稿叧绫?/鏂规硶]
 * @since  [浜у搧/妯″潡鐗堟湰]
 */
public class QueryKeyNoCaseComparator implements Comparator<String>
{
    /** {@inheritDoc} */
    
    @Override
    public int compare(String o1, String o2)
    {
        return o1.toLowerCase().compareTo(o2.toLowerCase());
    }
}
