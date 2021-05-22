package com.example.bookingbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RankingFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener {

    private RecyclerView recyclerView_ranking_all, recyclerView_ranking_novel, recyclerView_ranking_essay,
            recyclerView_ranking_bio, recyclerView_ranking_philo;
    private RecyclerViewAdapter adapter, adapter_novel, adapter_essay, adapter_bio, adapter_philo;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Items> books = new ArrayList<Items>();
    private ArrayList<Items> books_novel = new ArrayList<Items>();
    private ArrayList<Items> books_essay = new ArrayList<Items>();
    private ArrayList<Items> books_bio = new ArrayList<Items>();
    private ArrayList<Items> books_philo = new ArrayList<Items>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        for(int i=0; i<5; i++) {
//            books.add(new ItemBook(R.drawable.img_book_example));
//            books.add(new ItemBook(R.drawable.img_book_example2));
//        }

        arrayInit();

        //recyclerview 전체 랭킹 순위
        recyclerView_ranking_all = (RecyclerView)view.findViewById(R.id.rv_ranking);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_all.setHasFixedSize(true);
        recyclerView_ranking_all.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_all.setAdapter(adapter);

        //recyclerview 소설장르 인기순위
        recyclerView_ranking_novel = (RecyclerView)view.findViewById(R.id.rv_ranking_novel);
        adapter_novel = new RecyclerViewAdapter(getContext(), books_novel, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_novel.setHasFixedSize(true);
        recyclerView_ranking_novel.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_novel.setAdapter(adapter_novel);

        //recyclerview 에세이장르 인기순위
        recyclerView_ranking_essay = (RecyclerView)view.findViewById(R.id.rv_ranking_essay);
        adapter_essay = new RecyclerViewAdapter(getContext(), books_essay, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_essay.setHasFixedSize(true);
        recyclerView_ranking_essay.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_essay.setAdapter(adapter_essay);

        //recyclerview 전기장르 인기순위
        recyclerView_ranking_bio = (RecyclerView)view.findViewById(R.id.rv_ranking_bio);
        adapter_bio = new RecyclerViewAdapter(getContext(), books_bio, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_bio.setHasFixedSize(true);
        recyclerView_ranking_bio.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_bio.setAdapter(adapter_bio);

        //recyclerview 철학장르
        recyclerView_ranking_philo = (RecyclerView)view.findViewById(R.id.rv_ranking_philo);
        adapter_philo = new RecyclerViewAdapter(getContext(), books_philo, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_philo.setHasFixedSize(true);
        recyclerView_ranking_philo.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_philo.setAdapter(adapter_philo);

    }


    public void onBookClick(int position, ArrayList<Items> book) {
        Log.e(TAG, "onBookClick: 책 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("bookList", (Serializable)book.get(position));
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void arrayInit() {
        books.add(new Items("https://bookthumb-phinf.pstatic.net/cover/168/230/16823099.jpg?type=m1&udate=20210501",
                "오은영","17500","8934986654 9788934986652","http://book.naver.com/bookdb/book_detail.php?bid=16823099","15750",
                "김영사","채널A 〈요즘 육아 금쪽같은 내 새끼〉 SBS 〈우리 아이가 달라졌어요〉\n국민 육아멘토 오은영 박사가 알려주는 ‘부모의 말’\n육아 현실을 200퍼센트 반영한 130가지 한마디‘국민 육아멘토’ ‘대한민국 엄마·아빠들의 엄마’ 오은영 박사의『어떻게 말해줘야 할까』. 쉽게 따라 할 수 있는 ‘부모의 말’을... ",
                "어떻게 말해줘야 할까 (오은영의 현실밀착 육아회화)","20201025"));
        books.add(new Items("https://bookthumb-phinf.pstatic.net/cover/188/282/18828218.jpg?type=m1&udate=20210421",
                "소윤","15000","1197037152 9791197037153","http://book.naver.com/bookdb/book_detail.php?bid=18828218","13500",
                "북로망스","“존재만으로도 충분한 너에게 해주고 싶은 말”\n고단한 일상에서 가끔 우리는 각자의 빛을 잊고 살 때가 있다. 빛나야 하는 이유도 점점 내가 아닌 타인, 혹은 다른 이유가 되어버리는 세상. 세상이 제멋대로 정의한 거대한 별만 바라보느라, 내 안의 빛을 보고 있지 못한 우리에게 건네는 작가의 위로. 잊지 말자... ",
                "작은 별이지만 빛나고 있어 (소윤 에세이)","20210324"));
        books.add(new Items("https://bookthumb-phinf.pstatic.net/cover/190/075/19007535.jpg?type=m1&udate=20210518",
                "매트 헤이그","15800","1191056554 9791191056556","http://book.naver.com/bookdb/book_detail.php?bid=19007535", "14220",
                "인플루엔셜","그가 눈을 뜬 곳은 삶과 죽음 사이의 미스터리한 공간 ‘미드나잇 라이브러리’. 시간은 자정에서 멈춰 있다. 도서관 사서 엘름 부인의 안내로 노라는... “강렬한 존재감과 위대한 재능을 가진 소설가”(《뉴욕타임스》)로 평가받는 작가 매트 헤이그가 신작 《미드나잇 라이브러리》로 한국의 독자들을... ",
                "미드나잇 라이브러리 (Midnight Library)", "20210428"));
        books_novel.add(new Items("https://bookthumb-phinf.pstatic.net/cover/155/115/15511574.jpg?type=m1&udate=20191102",
                "넬레 노이하우스","12800","1158791178 9791158791179","http://book.naver.com/bookdb/book_detail.php?bid=15511574", "11520",
                "북로드","최신작 『잔혹한 어머니의 날(전2권)』이 북로드에서 번역 출간됐다. 속편은 전편을 뛰어넘기... 신작 『잔혹한 어머니의 날』에서도 독일 헤센 주의 프랑크푸르트와 타우누스의 동화 같은 마을을... 그런 악의 존재를 영리하고 노련하게 드러내고 있는 『잔혹한 어머니의 날』은 마지막 페이지를 덮는 순간... ",
                "잔혹한 어머니의 날 1 (넬레 노이하우스 장편소설)","20191007"));
        books_novel.add(new Items("https://bookthumb-phinf.pstatic.net/cover/154/764/15476484.jpg?type=m1&udate=20210508",
                "이찬혁","15000","1130625907 9791130625904","http://book.naver.com/bookdb/book_detail.php?bid=15476484","13500",
                "수카","철학적인 화두가 고스란히 배어있는 이찬혁의 문장들!천재적 감성의 아티스트, AKMU(악동뮤지션)의 이찬혁이 펴낸 첫 번째 소설 『물 만난 물고기』. 2019년 가을, 한날 발매된 AKMU(악동뮤지션) 정규앨범 《항해》와 세계관을 공유한 이 작품에는 세상을 향해 던지는 짙고 푸른 물음과 소중한 것을 지켜나가는 것의... ",
                "물 만난 물고기 (이찬혁 소설)","20190926"));
        books_novel.add(new Items( "https://bookthumb-phinf.pstatic.net/cover/155/099/15509932.jpg?type=m1&udate=20210111",
                "요나스 요나손","14800","8932919879 9788932919874","http://book.naver.com/bookdb/book_detail.php?bid=15509932","13320",
                "열린책들","창문 넘어 도망친 100세 노인이 돌아오다 베스트셀러 작가 요나스 요나손의 장편소설 『핵<을 들고 도망친 101세 노인』이 열린책들에서... 이번에는 백 한 살 생일날 열기구를 탔다가 조난당하며 새로운 모험을 시작한다. 『핵을 들고 도망친 101세 노인』은 요나손의 통산 네 번째 소설이다.",
                "핵을 들고 도망친 101세 노인","20190925"));
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
        books_bio.add(new Items("https://bookthumb-phinf.pstatic.net/cover/150/433/15043333.jpg?type=m1&udate=20210317",
                "최태성","15000","1130621960 9791130621968","http://book.naver.com/bookdb/book_detail.php?bid=15043333","13500",
                "다산초당","지난 20여 년간 500만 명의 가슴을 울린 대한민국 대표 역사 강사 최태성. 선택의 기로에 설 때마다 역사에서 답을 찾은 저자는 삶이라는 문제에 대한 가장 완벽한 해설서는 역사라고 말한다. 도저히 풀리지 않는 문제에 부딪쳤을 때 해설에서 도움을 얻듯, 우리보다 앞서 살았던 인물들의 선택과 그 결과가 담긴... ",
                "역사의 쓸모","20191122"));
        books_bio.add(new Items("https://bookthumb-phinf.pstatic.net/cover/154/776/15477639.jpg?type=m1&udate=20210208",
                "우종영", "16000","1196509484 9791196509484","http://book.naver.com/bookdb/book_detail.php?bid=15477639","14400",
                "메이븐","인생의 어려운 질문에 부딪칠 때마다 나는 나무에게서 그 해답을 얻었다!\n30년 동안 아픈 나무들을 돌봐 온 나무 의사 우종영. 그가 숲에서 배운 47가지 인생 수업 『나는 나무에게 인생을 배웠다』. 저자에게 있어 나무는 힘들고 어려운 일에 맞닥뜨릴 때마다 가장 현명한 답을 주는 스승이자 철학자였고, 곁에... ",
                "나는 나무에게 인생을 배웠다 (세상에서 가장 나이 많고 지혜로운 철학자, 나무로부터 배우는 단단한 삶의 태도들)","20190927"));
        books_bio.add(new Items("https://bookthumb-phinf.pstatic.net/cover/154/757/15475772.jpg?type=m1&udate=20200306",
                "한동일","15500","8954657877 9788954657877","http://book.naver.com/bookdb/book_detail.php?bid=15475772", "13950",
                "문학동네","이 시대를 살아가는 생활인들의 가슴에 와 닿는 로마법을 전하다!《라틴어 수업》으로 인문독자들을 열광하게 했던 한국인 최초, 동아시아 최초의 바티칸 대법원 로타 로마나 변호사 한동일이 인류법의 기원이자 인간다운 삶과 공동체를 이루어나가기 위한 로마인들의 치열한 고민의 기록인 로마법을 정리한... ",
                "로마법 수업 (흔들리지 않는 삶을 위한 천 년의 학교)","20190925"));
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
