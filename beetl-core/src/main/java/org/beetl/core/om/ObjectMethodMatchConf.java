/*
 [The "BSD license"]
 Copyright (c) 2011-2014 Joel Li (李家智)
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
     derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.beetl.core.om;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/** 用于本地调用类型转化
 * @author joelli
 *
 */
public class ObjectMethodMatchConf
{
	public Method method;
	//如何转化
	public int[] convert;
	// 是否需要转化
	public boolean isNeedConvert;
	//todo,取消
	public boolean isExactMatch = true;
	public final static int INT_CONVERT = 1;
	public final static int LONG_CONVERT = 2;
	public final static int DOUBLE_CONVERT = 3;
	public final static int FLOAT_CONVERT = 4;
	public final static int SHORT_CONVERT = 5;
	public final static int BIGDECIMAL_CONVERT = 6;

	public final static int NO_CONVERT = 0;

	public String toString()
	{
		String str = method.toString() + ",";
		for (int i : convert)
		{
			str = str + i + ",";
		}
		return str;
	}

	public Object invoke(Object o, Object[] parameter)
	{
		return null;
	}

	public String getOutputType(int i)
	{
		switch (convert[i])
		{

			case INT_CONVERT:
			{
				return ".intValue()";
			}
			case LONG_CONVERT:
			{
				return ".longValue()";
			}
			case DOUBLE_CONVERT:
			{
				return ".doubleValue()";
			}
			case FLOAT_CONVERT:
			{
				return ".floatValue()";
			}
			case SHORT_CONVERT:
			{
				return ".shortValue()";
			}

			case BIGDECIMAL_CONVERT:
			{
				return ".getBigDecimal()";
			}
			default:
			{
				throw new RuntimeException("not support converty type " + i);
			}

		}
	}

	public Object convert(Object o, int i)
	{
		switch (convert[i])
		{
			case NO_CONVERT:
			{
				return o;
			}
			case INT_CONVERT:
			{
				return ((Number) o).intValue();
			}
			case LONG_CONVERT:
			{
				return ((Number) o).longValue();
			}
			case DOUBLE_CONVERT:
			{
				return ((Number) o).doubleValue();
			}
			case FLOAT_CONVERT:
			{
				return ((Number) o).floatValue();
			}
			case SHORT_CONVERT:
			{
				return ((Number) o).shortValue();
			}

			case BIGDECIMAL_CONVERT:
			{
				return new BigDecimal(o.toString());
			}
			default:
			{
				throw new RuntimeException("not support converty type " + i);
			}

		}
	}

}