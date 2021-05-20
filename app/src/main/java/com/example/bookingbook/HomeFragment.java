package com.example.bookingbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener {

    private RecyclerView recyclerView_classic_novel, recyclerView_poetry, recyclerView_essay,
            recyclerView_history, recyclerView_philo;
    private RecyclerViewAdapter adapter, adapter_poetry, adapter_essay, adapter_history, adapter_philo;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Items> books = new ArrayList<Items>();
    private ArrayList<Items> books_essay = new ArrayList<Items>();
    private ArrayList<Items> books_poetry = new ArrayList<Items>();
    private ArrayList<Items> books_history = new ArrayList<Items>();
    private ArrayList<Items> books_philo = new ArrayList<Items>();
    private ConstraintLayout section;

    private String CLIENT_ID = "3_FP0gfD3YHrOdUGUrm0";
    private String CLIENT_SECRET = "eIZ969Ubz1";
    private String baseUrl = "https://openapi.naver.com/v1/search/";
    private HomeRequest homeRequest;

    private TextView txt_classic_novel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        homeRequest= retrofit.create(HomeRequest.class);

        Call<MovieResponse> callGetBook = homeRequest.getBook(CLIENT_ID, CLIENT_SECRET, "가", "count", "100020020");
        callGetBook.enqueue(retrofitCallback);

        section = (ConstraintLayout)view.findViewById(R.id.section_click);

        txt_classic_novel = (TextView)view.findViewById(R.id.txt_home_classic_novel);

        // 임시 클릭리스너(고전소설)
        txt_classic_novel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상세 액티비티로 이동
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                startActivity(intent);
            }
        });

//        for(int i=0; i<5; i++) {
//            books.add(new ItemBook(R.drawable.img_book_example));
//            books.add(new ItemBook(R.drawable.img_book_example2));
//        }
        arrayInit();


        //recyclerview 고전소설
        recyclerView_classic_novel = (RecyclerView)view.findViewById(R.id.rv_home_classic_nov);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_classic_novel.setHasFixedSize(true);
        recyclerView_classic_novel.setLayoutManager(linearLayoutManager);
        recyclerView_classic_novel.setAdapter(adapter);

        //recyclerview 에세이
        recyclerView_essay = (RecyclerView)view.findViewById(R.id.rv_home_essay);
        adapter_essay = new RecyclerViewAdapter(getContext(), books_essay, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_essay.setHasFixedSize(true);
        recyclerView_essay.setLayoutManager(linearLayoutManager);
        recyclerView_essay.setAdapter(adapter_essay);

        //recyclerview 시집
        recyclerView_poetry = (RecyclerView)view.findViewById(R.id.rv_home_poetry);
        adapter_poetry = new RecyclerViewAdapter(getContext(), books_poetry, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_poetry.setHasFixedSize(true);
        recyclerView_poetry.setLayoutManager(linearLayoutManager);
        recyclerView_poetry.setAdapter(adapter_poetry);

        //recyclerview 역사
        recyclerView_history = (RecyclerView)view.findViewById(R.id.rv_home_history);
        adapter_history = new RecyclerViewAdapter(getContext(), books_history, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_history.setHasFixedSize(true);
        recyclerView_history.setLayoutManager(linearLayoutManager);
        recyclerView_history.setAdapter(adapter_history);

        //recyclerview 철학
        recyclerView_philo = (RecyclerView)view.findViewById(R.id.rv_home_philo);
        adapter_philo = new RecyclerViewAdapter(getContext(), books_philo, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_philo.setHasFixedSize(true);
        recyclerView_philo.setLayoutManager(linearLayoutManager);
        recyclerView_philo.setAdapter(adapter_philo);

        //section clicked
        section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 섹션 액티비티로 이동
                Intent intent = new Intent(getActivity(), SectionActivity.class);
                startActivity(intent);
            }
        });

    }

    private Callback<MovieResponse> retrofitCallback = new Callback<MovieResponse>() {
        @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
            if (response.isSuccessful()) {
                int total = response.body().getItems().length;
                Items[] booksSearched = response.body().getItems();
                for (int i=0; i<total; i++) {
                    Log.d("Response", booksSearched[i].image);
                    Items book = booksSearched[i];
                    books.add(new Items(book.getImage(), book.getAuthor(), book.getPrice(), book.getIsbn(), book.getLink(), book.getDiscount(),
                            book.getPublisher(), book.getDescription(), book.getTitle(), book.getPubdate()));
                    adapter.notifyDataSetChanged();
                }
            }
            else {
                Log.d("ResponseError", response.raw().toString());
            }

        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            Log.d("Response", "Fail");
            t.printStackTrace();

        }
    };

    @Override
    public void onBookClick(int position) {

    }

    // 리싸이클러뷰 아이템 클릭

    public void onBookClick(int position, ArrayList<Items> book) {
        Log.e(TAG, "onBookClick: 책 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("bookList", book);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void arrayInit() {
        books_essay.add(new Items("https://bookthumb-phinf.pstatic.net/cover/097/434/09743475.jpg?type=m1&udate=20210328",
                "포터 스타일", "16800", "1158510241 9791158510244", "http://book.naver.com/bookdb/book_detail.php?bid=9743475", "15120",
                "토네이도", "#기억으로 나를 채우다\n\n“당신 삶의 소중한 변화와 성장을 지금 이 책에 기록하세요”\n미국·영국 아마존 500주 연속 베스트셀러\n전 세계에서 가장 사랑받는, 가장 아름다운 다이어리북2010년 미국에서 출간되어 영국, 유럽, 전 세계 다이어리북 시장을 석권한 《5년 후 나에게 : Q&A A DAY》가 2021년을... ",
                "5년 후 나에게 Q&A a Day", "20191101"));
        books_essay.add(new Items("https://bookthumb-phinf.pstatic.net/cover/064/427/06442753.jpg?type=m1&udate=20210301",
                "윤석철", "14000", "8960864234 9788960864238", "http://book.naver.com/bookdb/book_detail.php?bid=6442753", "12600",
                "위즈덤하우스", "출간된 지 6년이 지나 다시 한 번 화제의 책으로 떠오른 한국 경영학계의 구루 윤석철 교수의 네 번째 10년 주기 걸작. 자연과학과 사회과학의 경계를 넘나들며 한국 경영학에 통찰과 활력을 불어넣어온 저자는 인간의 삶을 수단매체와 목적함수라는 2개의 개념으로 분석하여, 이것을 바탕으로 삶에 필요한... ",
                "삶의 정도 (윤석철 교수 제4의 10년 주기 작)", "20110110"));
        books_essay.add(new Items("https://bookthumb-phinf.pstatic.net/cover/001/420/00142093.jpg?type=m1&udate=20150715",
                "랑카 비엘작", "7000", "8952736044 9788952736048", "http://book.naver.com/bookdb/book_detail.php?bid=142093", "6300",
                "시공사", "사회가 태어나고 성장하고 쇠퇴하고 죽듯이, 언어 또한 잉테, 성장, 소멸, 화석의 과정을 거친다. 저자는 이러한 인간 언어와 관련하여 언어의 특수성, 언어의 습득, 언어, 사회의 기본적 연결고리와 지정학, 소멸 등 언어의 다양한 측면에 대하여 고찰한다. 사진과 자료를 삽입하여 독자로 하여금 복잡한 이론을... ",
                "언어의 다양한 풍경", "20040130"));
        books_poetry.add(new Items("https://bookthumb-phinf.pstatic.net/cover/091/274/09127431.jpg?type=m1&udate=20191202",
                "김용택", "12800", "8959139300 9788959139309", "http://book.naver.com/bookdb/book_detail.php?bid=9127431", "11520",
                "위즈덤하우스","김용택 시인이 엄선한 111편의 시를 손으로 읽고 마음으로 새겨보는 시간!‘섬진강 시인’이란 별칭으로 잘 알려진 시인 김용택이 여러 문인의 시를 직접 읽고 따라 써보며 ‘독자들도 꼭 한번은 따라 써보길 바라는 마음’으로 101편의 시를 엄선해 수록한 책이다. 책의 왼쪽 페이지에는 시의 원문을, 오른쪽... ",
                "어쩌면 별들이 너의 슬픔을 가져갈지도 몰라 (김용택의 꼭 한번 필사하고 싶은 시)", "20150604"));
        books_poetry.add(new Items("https://bookthumb-phinf.pstatic.net/cover/166/844/16684449.jpg?type=m1&udate=20210414",
                "류시화","13000", "1190382261 9791190382267", "http://book.naver.com/bookdb/book_detail.php?bid=16684449","11700",
                "수오서재", "“날개를 주웠다, 내 날개였다.”시를 읽는 것은 자기 자신으로 돌아오는 것이고, 세상을 경이롭게 여기는 것이며, 여러 색의 감정을 경험하는 것이다.... ‘마음챙김의 삶을 살고 있는가, 마음놓침의 시간을 보내고 있는가?’\n사회적 거리두기와 삶에 대한 성찰이 어느 때보다 필요한 지금, 손 대신 시를 건네는... ",
                "마음챙김의 시", "20200917"));
        books_poetry.add(new Items("https://bookthumb-phinf.pstatic.net/cover/163/912/16391270.jpg?type=m1&udate=20201112",
                "한정원","16000","1196517193 9791196517199","http://book.naver.com/bookdb/book_detail.php?bid=16391270","14400",
                "시간의흐름", "산책에서 돌아올 때마다 나는 전과 다른 사람이 된다.시가 산책이 될 때, 산책이 시가 될 때…<b>시</b>를 읽는다는 건 무엇일까? 그럼, 산책을 한다는 건? 그건 어쩌면 고요한 하강과, 존재의 밑바닥에 고이는 그늘을 외면하지 않는 묵묵함의 다른 말일지도 모른다. 그건 결국 산다는 것은 무엇일까에 대한 질문일... ",
                "시와 산책 (Poetry and Walks)", "20200630"));
        books_history.add(new Items("https://bookthumb-phinf.pstatic.net/cover/150/433/15043333.jpg?type=m1&udate=20210317",
                "최태성", "15000", "1130621960 9791130621968", "http://book.naver.com/bookdb/book_detail.php?bid=15043333", "13500",
                "다산초당", "길을 잃고 방황할 때마다 나는 역사에서 답을 찾았다!\n수백 년 전 이야기로 오늘의 고민을 해결하는 방법을 알려주는 실용적인 역사 사용 설명서 『역사의 쓸모』. 지난 20여 년간 500만 명의 가슴을 울린 대한민국 대표 역사 강사 최태성. 선택의 기로에 설 때마다 역사에서 답을 찾은 저자는 삶이라는 문제에 대한... ",
                "역사의 쓸모", "20191122"));
        books_history.add(new Items("https://bookthumb-phinf.pstatic.net/cover/163/288/16328824.jpg?type=m1&udate=20210511",
                "빌 브라이슨",  "25000", "8972917117 9788972917113", "http://book.naver.com/bookdb/book_detail.php?bid=16328824","22500",
                "까치","것의 역사」\n오늘의 과학 지식을 반영한, 새로운 디자인의 개역판 출시2003년 출간된 이래 전 세계의 많은 독자들의 사랑을 받아온 빌 브라이슨의 『거의 모든 것의 역사(개역판)』 이번 개역판은 빠르게... 그렇다고 따분한 과학의 역사를 지루하게 소개하는 것도 아니다. 우리가 왜 우주와 지구의 역사를 알고... ",
                "거의 모든 것의 역사(개역개정판) (개역판)", "20200410"));
        books_history.add(new Items("https://bookthumb-phinf.pstatic.net/cover/088/821/08882146.jpg?type=m1&udate=20170808",
                "에드워드 카", "12000","8972915815 9788972915812", "http://book.naver.com/bookdb/book_detail.php?bid=8882146","10800",
                "까치","역사란 ‘과거와 현재의 대화’ 또는 ‘과거의 사실과 현재의 역사가의 대화’라는 것은 누구에게든 널리 회자되어온, 역사에 대한 카의 유명한 정의이다. 그러나 그 두 항목 중에서 카가 강조하는 것은 과거 자체 혹은 과거의 사실이 아니라 그것을 가지고 역사담론과 역사지식을 생산하는 ‘현재의 역사가’... ",
                "역사란 무엇인가 (독점계약 번역)","20150316"));
        books_philo.add(new Items("https://bookthumb-phinf.pstatic.net/cover/030/866/03086604.jpg?type=m1&udate=20201221",
                "사마천","30000","8937425963 9788937425967","http://book.naver.com/bookdb/book_detail.php?bid=3086604","27000",
                "민음사","개정 작업으로 한층 더 생생하게 복원된\n최고의 『사기 열전』 번역본\n\n중국 정사의 효시\n동양 역사학의 전범典範\n\n대표적 인문학 스테디셀러로서 판과 쇄를 거듭하며 많은 독자들의 사랑을 받아 온 김원중 교수의 『사기 열전』이 개정2판으로 민음사에서 다시금 출간되었다. 2011년 개인으로서는 세계 최초로... ",
                "사기열전 1","20200810"));
        books_philo.add(new Items("https://bookthumb-phinf.pstatic.net/cover/155/098/15509866.jpg?type=m1&udate=20200702",
                "김재인",  "18000", "8962623048 9788962623048", "http://book.naver.com/bookdb/book_detail.php?bid=15509866",
                "16200","동아시아", "깊이 있고 정확하면서도 친절한 철학 개론서!《안티 오이디푸스》, 《천 개의 고원》을 번역한 철학자 김재인이 중요하다고 생각한 15가지 철학이 탄생한 순간을 살펴보면서 서양 철학사 전반을 꿰뚫는 『생각의 싸움』. 시대의 필요에 따라 치열하게 묻고 답하며, 당시 주도적인 통념에 맞서 생각의 싸움을... ",
                "생각의 싸움 (인류의 진보를 이끈 15가지 철학의 멋진 장면들)", "20190924"));
        books_philo.add(new Items("https://bookthumb-phinf.pstatic.net/cover/154/743/15474342.jpg?type=m1&udate=20200928",
                "배철현","17000","8950982676 9788950982676","http://book.naver.com/bookdb/book_detail.php?bid=15474342","15300",
                "21세기북스","“마음의 평정을 어떻게 찾을 것인가” 하루 10분, 고요하게 나를 지켜내는 힘  삶을 방해하는 것들로부터 나를 고요하게 지켜내는 힘에 대해 이야기하는 고전문헌학자 배철현의 『정적』. 인문 에세이의 새로운 지평을 연 《심연》, 《수련》에 이은 세 번째 책으로, 자기 자신을 위한 길잡이이자 도우미인 완벽... ",
                "정적 (나를 변화시키는 조용한 기적)", "20190911"));
    }


}
