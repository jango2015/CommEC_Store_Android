package store.commec.taojin.com.cn.commecstore;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);

        SampleFragmentPagerAdapter pagerAdapter =
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), this);

        mViewPager.setAdapter(pagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }

        mViewPager.setCurrentItem(3);
    }


    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;

        private String tabTitles[] = new String[] {
                getString(R.string.tab_order_management),
                getString(R.string.tab_order_search),
                getString(R.string.tab_shop_management),
                getString(R.string.tab_my_setting)
        };

        private int imageResId[] = new int[] {
                R.drawable.tab_main_order_management,
                R.drawable.tab_main_order_search,
                R.drawable.tab_main_shop_management,
                R.drawable.tab_main_my_setting
        };

        private Context context;

        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.view_main_tab_item, null);

            // Tab icon setting
            ImageView img = (ImageView) v.findViewById(R.id.iv_tab_icon);
            img.setImageResource(imageResId[position]);

            // Tab text setting
            TextView tv = (TextView) v.findViewById(R.id.tv_tab_txt);
            tv.setText(tabTitles[position]);

            // Tab text color controller
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_selected}, // pressed
                    new int[] {}
            };
            int[] colors = new int[] {
                    getResources().getColor(R.color.color_e96d1f),
                    getResources().getColor(R.color.color_606469),
            };
            ColorStateList list = new ColorStateList(states, colors);
            tv.setTextColor(list);

            return v;
        }

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position + 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    public static class PageFragment extends Fragment {
        public static final String ARG_PAGE = "ARG_PAGE";

        private int mPage;

        public static PageFragment newInstance(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            PageFragment fragment = new PageFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = null;
            if (mPage == 1) {
                view = inflater.inflate(R.layout.content_order_management, container, false);
//                TextView textView = (TextView) view.findViewById(R.id.section_label);
//                textView.setText("Home");

            } else if (mPage == 2) {
                view = inflater.inflate(R.layout.content_order_search, container, false);
//                TextView textView = (TextView) view.findViewById(R.id.section_label);
//                textView.setText("Search");

            } else if (mPage == 3) {
                view = inflater.inflate(R.layout.content_shop_management, container, false);
//                TextView textView = (TextView) view.findViewById(R.id.section_label);
//                textView.setText("Cart");

            } else if (mPage == 4) {
                view = inflater.inflate(R.layout.content_my_setting, container, false);
            }

            return view;
        }
    }

}