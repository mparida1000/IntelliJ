package com.scaler.string;

import java.util.ArrayList;
import java.util.List;

public class ZAlgo {
	
	public static List<Integer> search(String text, String pattern) {
	    String s = pattern + "$" + text;
	    int n = s.length();
	    int[] z = new int[n];
	    List<Integer> matches = new ArrayList<>();
	    
	    int l = 0, r = 0;
	    for (int i = 1; i < n; i++) {
	        if (i > r) {
	            l = r = i;
	            while (r < n && s.charAt(r - l) == s.charAt(r)) r++;
	            z[i] = r - l;
	            r--;
	        } else {
	            int k = i - l;
	            if (z[k] < r - i + 1) {
	                z[i] = z[k];
	            } else {
	                l = i;
	                while (r < n && s.charAt(r - l) == s.charAt(r)) r++;
	                z[i] = r - l;
	                r--;
	            }
	        }
	        if (z[i] == pattern.length()) {
	            matches.add(i - pattern.length() - 1);
	        }
	    }
	    
	    return matches;
	}
	
	public static void main(String[] args) {
		List<Integer> ls = search("dskj_feokpao_feopksl", "feo");
		//List<Integer> ls = search("lorem ipsum dolor sit amet, consectetuer adipiscing elit. aenean commodo ligula eget dolor. aenean massa. cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. nulla consequat massa quis enim. donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. in enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. nullam dictum felis eu pede mollis pretium. integer tincidunt. cras dapibus. vivamus elementum semper nisi. aenean vulputate eleifend tellus. aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. phasellus viverra nulla ut metus varius laoreet. quisque rutrum. aenean imperdiet. etiam ultricies nisi vel augue. curabitur ullamcorper ultricies nisi. nam eget dui. etiam rhoncus. maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. maecenas nec odio et ante tincidunt tempus. donec vitae sapien ut libero venenatis faucibus. nullam quis ante. etiam sit amet orci eget eros faucibus tincidunt. duis leo. sed fringilla mauris sit amet nibh. donec sodales sagittis magna. sed consequat, leo eget bibendum sodales, augue velit cursus nunc,", "nu");
		for(Integer i : ls) {
			System.out.println(i + " ");
		}
	}

}
