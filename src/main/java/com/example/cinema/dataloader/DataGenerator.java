package com.example.cinema.dataloader;

import com.example.cinema.reservation.domain.Reservation;
import com.example.cinema.reservation.domain.ReservationRepository;
import com.example.cinema.theater.domain.Theater;
import com.example.cinema.theater.domain.TheaterRepository;
import com.example.cinema.member.domain.Member;
import com.example.cinema.member.domain.MemberRepository;
import com.example.cinema.movie.domain.actor.Actor;
import com.example.cinema.movie.domain.actor.ActorRepository;
import com.example.cinema.movie.domain.director.Director;
import com.example.cinema.movie.domain.director.DirectorRepository;
import com.example.cinema.movie.domain.movie.*;
import com.example.cinema.screening.domain.Screening;
import com.example.cinema.screening.domain.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.cinema.member.domain.Gender.*;
import static com.example.cinema.member.domain.Role.*;
import static com.example.cinema.movie.domain.movie.MovieRating.*;

@Component
@RequiredArgsConstructor
public class DataGenerator {

    private final MovieDirectorRepository movieDirectorRepository;
    private final ReservationRepository reservationRepository;
    private final MovieActorRepository movieActorRepository;
    private final DirectorRepository directorRepository;
    private final ScreeningRepository screeningRepository;
    private final TheaterRepository cinemaRepository;
    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void generateTestData() {
        Member 관리자 = memberRepository.save(new Member(passwordEncoder.encode("admin"), "관리자", "admin@admin.com", MAN, LocalDate.of(1111, 1, 1), ADMIN)).generateMemberNumber();
        Member 김철수 = memberRepository.save(new Member(passwordEncoder.encode("test1"), "김철수", "cndghks0213@gmail.com", MAN, LocalDate.of(1999, 1, 1), MEMBER)).generateMemberNumber();
        Member 김짱구 = memberRepository.save(new Member(passwordEncoder.encode("test2"), "김짱구", "test2@test.com", MAN, LocalDate.of(2000, 6, 1), MEMBER)).generateMemberNumber();
        Member 김훈이 = memberRepository.save(new Member(passwordEncoder.encode("test3"), "김훈이", "test3@test.com", MAN, LocalDate.of(1997, 7, 3), MEMBER)).generateMemberNumber();
        Member 김맹구 = memberRepository.save(new Member(passwordEncoder.encode("test4"), "김맹구", "test4@test.com", MAN, LocalDate.of(1998, 1, 5), MEMBER)).generateMemberNumber();
        Member 김영희 = memberRepository.save(new Member(passwordEncoder.encode("test5"), "김영희", "test5@test.com", WOMAN, LocalDate.of(1996, 11, 11), MEMBER)).generateMemberNumber();
        Member 김유리 = memberRepository.save(new Member(passwordEncoder.encode("test6"), "김유리", "test6@test.com", WOMAN, LocalDate.of(2001, 12, 25), MEMBER)).generateMemberNumber();
        Member 김수지 = memberRepository.save(new Member(passwordEncoder.encode("test7"), "김수지", "test7@test.com", WOMAN, LocalDate.of(2005, 1, 31), MEMBER)).generateMemberNumber();
        Member 나미리 = memberRepository.save(new Member(passwordEncoder.encode("test8"), "나미리", "test8@test.com", WOMAN, LocalDate.of(1991, 2, 21), MEMBER)).generateMemberNumber();
        Member 신형만 = memberRepository.save(new Member(passwordEncoder.encode("test9"), "신형만", "test9@test.com", MAN, LocalDate.of(1995, 4, 1), MEMBER)).generateMemberNumber();
        Member 봉미선 = memberRepository.save(new Member(passwordEncoder.encode("test10"), "봉미선", "test10@test.com", WOMAN, LocalDate.of(1991, 8, 31), MEMBER)).generateMemberNumber();

        // Theather
        Theater 제1상영관 = cinemaRepository.save(new Theater("1관", 50));
        Theater 제2상영관 = cinemaRepository.save(new Theater("2관", 50));
        Theater 제3상영관 = cinemaRepository.save(new Theater("3관", 50));
        Theater 제4상영관 = cinemaRepository.save(new Theater("4관", 50));
        Theater 제5상영관 = cinemaRepository.save(new Theater("5관", 50));

        // Movie
        LocalDate today = LocalDate.now();
        // 상영종료
        Movie 범죄도시1 = movieRepository.save(new Movie("범죄도시1", today.minusDays(14), today.minusDays(5), 60, G_RATED,
                "2004년 서울… 하얼빈에서 넘어와 단숨에 기존 조직들을 장악하고 가장 강력한 세력인 춘식이파 보스 ‘황사장(조재윤 분)’까지 위협하며 도시 일대의 " +
                        "최강자로 급부상한 신흥범죄조직의 악랄한 보스 ‘장첸(윤계상 분)’. 대한민국을 뒤흔든 ‘장첸(윤계상 분)’ 일당을 잡기 위해 오직 주먹 한방으로 도시의 평화를 " +
                        "유지해 온 괴물형사 ‘마석도(마동석 분)’와 인간미 넘치는 든든한 리더 ‘전일만(최귀화 분)’ 반장이 이끄는 강력반은 나쁜 놈들을 한방에 쓸어버릴 끝.짱.나.는. " +
                        "작전을 세우는데… 통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!"));

        Movie 범죄도시2 = movieRepository.save(new Movie("범죄도시2", today.minusDays(15), today.minusDays(6), 60, G_RATED,
                "가리봉동 소탕작전 후 4년 뒤, 금천서 강력반은 베트남으로 도주한 용의자를 인도받아 오라는 미션을 받는다. 괴물형사 ‘마석도’(마동석)와 ‘전일만’(최귀화) 반장은" +
                        " 현지 용의자에게서 수상함을 느끼고, 그의 뒤에 무자비한 악행을 벌이는 ‘강해상’(손석구)이 있음을 알게 된다. ‘마석도’와 금천서 강력반은 한국과 베트남을 오가며 역대급" +
                        " 범죄를 저지르는 ‘강해상’을 본격적으로 쫓기 시작하는데... 나쁜 놈들 잡는 데 국경 없다! 통쾌하고 화끈한 범죄 소탕 작전이 다시 펼쳐진다!"));
        Movie 닥터_스트레인지 = movieRepository.save(new Movie("닥터 스트레인지: 대혼돈의 멀티버스", today.minusDays(16), today.minusDays(7), 60, G_RATED,
                "끝없이 균열되는 차원과 뒤엉킨 시공간의 멀티버스가 열리며 오랜 동료들, 그리고 차원을 넘어 들어온 새로운 존재들을 맞닥뜨리게 된 ‘닥터 스트레인지’. 대혼돈 속, 그는 예상치 못한 극한의 적과 맞서 싸워야만 하는데…."));

        Movie 인터스텔라 = movieRepository.save(new Movie("인터스텔라", today.minusDays(14), today.minusDays(5), 60, G_RATED,
                "점점 황폐해져가는 지구를 대체할 인류의 터전을 찾기 위해 새롭게 발견된 웜홀을 통해 항성 간(Interstellar) 우주 여행을 떠나는 탐험가들의 모험이 연대기 순으로 그려진다."));

        Movie 아이언맨 = movieRepository.save(new Movie("아이언맨", today.minusDays(15), today.minusDays(6), 60, G_RATED,
                "천재적인 두뇌와 재능으로 세계 최강의 무기업체를 이끄는 CEO이자, 타고난 매력으로 셀러브리티 못지않은 화려한 삶을 살아가던 억만장자 토니 스타크. 아프가니스탄에서 자신이 개발한 " +
                        "신무기 발표를 성공리에 마치고 돌아가던 그는 게릴라군의 갑작스런 공격에 의해 가슴에 치명적인 부상을 입고 게릴라군에게 납치된다."));

        Movie 인크레더블_헐크 = movieRepository.save(new Movie("인크레더블 헐크", today.minusDays(16), today.minusDays(7), 60, G_RATED,
                "실험 중 감마선에 노출된 이후 분노를 통제할 수 없는 상태가 되면 녹색 괴물 ‘헐크’로 변하게 되는 브루스 배너(에드워드 노튼). 필사적으로 치료제 개발에 매달리지만, 그의 능력을 " +
                        "이용하려는 정부 군대의 끈질긴 추격을 받고 쫓기게 된다. 한편 헐크의 거대한 힘의 비밀을 파헤치던 이들은 헐크보다 더 파괴적인 ‘어보미네이션’을 만드는데 성공, 도시 전체를 집어삼키려 한다. " +
                        "이에 브루스 배너는 정상적인 인생을 되찾으려던 계획을 버리고, 어보미네이션과 정부 군대, 그리고 자기 자신과의 마지막 대결을 준비하는데..."));

        // 상영중
        Movie 토르_천둥의_신 = movieRepository.save(new Movie("토르: 천둥의 신", today.minusDays(5), today.plusDays(4), 60, G_RATED,
                "신의 세계 ‘아스가르드’의 후계자로 강력한 파워를 지닌 천둥의 신 ‘토르’. 평소 거침없는 성격의 소유자인 토르는 신들간의 전쟁을 일으킨 죄로 신의 자격을 박탈당한 채 지구로 추방당한다. " +
                        "힘의 원천인 해머 ‘묠니르’도 잃어버린 채 하루 아침에 평범한 인간이 되어버린 토르는 혼란스러움을 뒤로 한 채 지구에서 처음 마주친 과학자 ‘제인’ 일행과 함께 하며 인간 세계에 적응해 나가기 시작한다. " +
                        "하지만 그 사이 아스가르드는 후계자 자리를 노리는 ‘로키’의 야욕으로 인해 혼란에 빠진다. 후계자로 지목된 자신의 형 토르를 제거하려는 로키는 마침내 지구에까지 무차별적인 공격을 시작한다. 자신의 존재 때문에 " +
                        "지구에 거대한 위험이 닥치고 있음을 알게 된 토르. 그런 그의 앞에 보다 강력한 파괴력의 상대가 등장하는데… 두 개의 세계, 한 명의 영웅 모두의 운명을 건 최후의 격돌이 시작된다!"));

        Movie 아이언맨3 = movieRepository.save(new Movie("아이언맨 3", today.minusDays(5), today.plusDays(4), 60, G_RATED,
                "<어벤져스> 뉴욕 사건의 트라우마로 인해 영웅으로서의 삶에 회의를 느끼는 토니 스타크(로버트 다우니 주니어). 그가 혼란을 겪는 사이 최악의 테러리스트 만다린(벤 킹슬리)을 내세운 익스트리미스 집단 AIM이 스타크 " +
                        "저택에 공격을 퍼붓는다. 이 공격으로 그에게 남은 건 망가진 수트 한벌 뿐. 모든 것을 잃어버린 그는 다시 테러의 위험으로부터 세계와 사랑하는 여인(기네스 팰트로)를 지켜내야 하는 동시에 머릿속을 떠나지 않던 한가지 물음의" +
                        " 해답도 찾아야만 한다. 과연 그가 아이언맨인가? 수트가 아이언맨인가?"));

        Movie 토르_다크_월드 = movieRepository.save(new Movie("토르: 다크 월드", today.minusDays(4), today.plusDays(5), 60, G_RATED,
                "<어벤져스>의 뉴욕 사건 후, 다시 신들의 고향인 아스가르드 왕국으로 돌아간 토르(크리스 헴스워스)와 로키(톰 히들스턴). 지구를 위협한 로키는 지하 감옥에 갇히고, 토르는 아버지 오딘(안소니 홉킨스)과 함께 우주의 " +
                        "질서를 재정립하기 위해 나선다. 1년 후, 지구에 혼자 남은 제인(나탈리 포트만)은 우연히 태초부터 존재해왔던 어둠의 종족 ‘다크 엘프’의 무기 ‘에테르’를 얻게 된다. 이 사실을 안 ‘다크 엘프’의 리더 ‘말레키스’는 ‘에테르’를" +
                        " 되찾기 위해 제인과 아스가르드를 공격하고, 토르는 사랑하는 여인 제인과 아스가르드 왕국을 지키기 위해 로키에게 위험한 동맹을 제안하게 된다."));

        Movie 어벤져스 = movieRepository.save(new Movie("어벤져스: 엔드게임", today.minusDays(6), today.plusDays(3), 60, G_RATED,
                "지구의 안보가 위협당하는 위기의 상황에서 슈퍼히어로들을 불러모아 세상을 구하는, 일명 [어벤져스] 작전. 에너지원 ‘큐브’를 이용한 적의 등장으로 인류가 위험에 처하자 국제평화유지기구인 쉴드 (S.H.I.E.L.D)의 국장 닉 퓨리(사무엘 L.잭슨)는 " +
                        "[어벤져스] 작전을 위해 전 세계에 흩어져 있던 슈퍼히어로들을 찾아나선다. 아이언맨(로버트 다우니 주니어)부터 토르(크리스 헴스워스), 헐크(마크 러팔로), 캡틴 아메리카(크리스 에반스)는 물론, 쉴드의 요원인 블랙 위도우(스칼렛 요한슨), " +
                        "호크 아이(제레미 레너)까지, 최고의 슈퍼히어로들이 [어벤져스]의 멤버로 모이게 되지만, 각기 개성이 강한 이들의 만남은 예상치 못한 방향으로 흘러가는데… 지구의 운명을 건 거대한 전쟁 앞에 [어벤져스] 작전은 성공할 수 있을까?"));

        Movie 타이타닉 = movieRepository.save(new Movie("타이타닉", today.minusDays(4), today.plusDays(5), 60, G_RATED,
                "우연한 기회로 티켓을 구해 타이타닉호에 올라탄 자유로운 영혼을 가진 화가 잭(레오나르도 디카프리오)은 막강한 재력의 약혼자와 함께 1등실에 승선한 로즈(케이트 윈슬렛)에게 한 눈에 반한다. 진실한 사랑을 꿈꾸던 로즈 또한 생애 처음 " +
                        "황홀한 감정에 휩싸이고, 둘은 운명 같은 사랑에 빠지는데…"));

        Movie 인셉션 = movieRepository.save(new Movie("인셉션", today.minusDays(7), today.plusDays(2), 60, G_RATED,
                "타인의 꿈에 들어가 생각을 훔치는 특수 보안요원 코브. 그를 이용해 라이벌 기업의 정보를 빼내고자 하는 사이토는 코브에게 생각을 훔치는 것이 아닌, 생각을 심는 ‘인셉션’ 작전을 제안한다. 성공 조건으로 국제적인 수배자가 되어있는 " +
                        "코브의 신분을 바꿔주겠다는 거부할 수 없는 제안을 하고, 사랑하는 아이들에게 돌아가기 위해 그 제안을 받아들인다. 최강의 팀을 구성, 표적인 피셔에게 접근해서 ‘인셉션’ 작전을 실행하지만 예기치 못한 사건들과 마주하게 되는데… 꿈 VS " +
                        "현실 시간, 규칙, 타이밍 모든 것이 완벽해야만 하는, 단 한 번도 성공한 적 없는 ‘인셉션’ 작전이 시작된다!"));

        Movie 올드보이 = movieRepository.save(new Movie("올드보이", today.minusDays(4), today.plusDays(5), 60, G_RATED,
                "술 좋아하고 떠들기 좋아하는 오.대.수. 본인의 이름풀이를 '오늘만 대충 수습하며 살자'라고 이죽거리는 이 남자는 아내와 어린 딸아이를 가진 지극히 평범한 샐러리맨이다. 어느 날, 술이 거나하게 취해 집에 돌아가는 길에 존재를 " +
                        "알 수 없는 누군가에게 납치, 사설 감금방에 갇히게 되는데. 언뜻 보면 싸구려 호텔방을 연상케 하는 감금방. 중국집 군만두만을 먹으며 8평이라는 제한된 공간에서 그가 할 수 있는 일이라곤, 텔레비전 보는 게 전부..."));

        // 상영예정
        Movie 다크나이트 = movieRepository.save(new Movie("다크나이트", today.plusDays(2), today.plusDays(11), 60, G_RATED,
                "정의로운 지방 검사 ‘하비 덴트’, ‘짐 고든’ 반장과 함께 범죄 소탕 작전을 펼치며 범죄와 부패로 들끓는 고담시를 지켜나가는 ‘배트맨’ 그러던 어느 날, 살아남기 위해 발버둥치던 범죄 조직은 배트맨을 제거하기 위해 광기어린 악당" +
                        " ‘조커’를 끌어들이고 정체를 알 수 없는 조커의 등장에 고담시 전체가 깊은 혼돈 속으로 빠져든다. 급기야 배트맨을 향한 강한 집착을 드러낸 조커는 그가 시민들 앞에 정체를 밝힐 때까지 매일 새로운 사람들을 죽이겠다 선포하고 배트맨은" +
                        " 사상 최악의 악당 조커를 막기 위해 자신의 모든 것을 내던진 마지막 대결을 준비한다."));

        Movie 세_얼간이 = movieRepository.save(new Movie("세 얼간이", today.plusDays(3), today.plusDays(12), 60, G_RATED,
                "천재들만 간다는 일류 명문대 ICE, 성적과 취업만을 강요하는 학교를 발칵 뒤집어 놓은 대단한 녀석 란초! 아버지가 정해준 꿈, `공학자`가 되기 위해 정작 본인이 좋아하는 일은 포기하고 공부만하는 파파보이 파르한! 찢어지게 가난한 집, " +
                        "병든 아버지와 식구들을 책임지기 위해 무조건 대기업에 취직해야만 하는 라주! 친구의 이름으로 뭉친 `세 얼간이`! 삐딱한 천재들의 진정한 꿈을 찾기 위한 세상 뒤집기 한판이 시작된다!"));

        Movie 기생충 = movieRepository.save(new Movie("기생충", today.plusDays(4), today.plusDays(13), 60, G_RATED,
                "전원백수로 살 길 막막하지만 사이는 좋은 기택(송강호) 가족. 장남 기우(최우식)에게 명문대생 친구가 연결시켜 준 고액 과외 자리는 모처럼 싹튼 고정수입의 희망이다. 온 가족의 도움과 기대 속에 박사장(이선균) 집으로 향하는 기우. " +
                        "글로벌 IT기업 CEO인 박사장의 저택에 도착하자 젊고 아름다운 사모님 연교(조여정)가 기우를 맞이한다. 그러나 이렇게 시작된 두 가족의 만남 뒤로, 걷잡을 수 없는 사건이 기다리고 있었으니…"));

        Movie 아바타 = movieRepository.save(new Movie("아바타", today.plusDays(1), today.plusDays(10), 60, G_RATED,
                "터미네이터, 타이타닉으로 유명한 제임스 카메론의 SF 장르의 영화이자 아바타 시리즈의 첫 번째 작품이다."));

        Movie 쇼생크_탈출 = movieRepository.save(new Movie("쇼생크 탈출", today.plusDays(2), today.plusDays(11), 60, G_RATED,
                "촉망 받던 은행 부지점장 ‘앤디(팀 로빈슨 分)’는 아내와 그 애인을 살해한 혐의로 종신형을 받고 쇼생크 교도소에 수감된다. 강력범들이 수감된 이곳에서 재소자들은 짐승 취급 당하고, 혹여 간수 눈에 잘못 보였다가는 개죽음 당하기 십상이다. " +
                        "처음엔 적응 못하던 ‘앤디’는 교도소 내 모든 물건을 구해주는 ‘레드(모건 프리먼 分)’와 친해지며 교도소 생활에 적응하려 하지만, 악질 재소자에게 걸려 강간까지 당한다. 그러던 어느 날, 간수장의 세금 면제를 도와주며 간수들의 비공식 회계사로 " +
                        "일하게 되고, 마침내는 소장의 검은 돈까지 관리해주게 된다. 덕분에 교도소 내 도서관을 열 수 있게 되었을 무렵, 신참내기 ‘토미(길 벨로우스 分)’로부터 ‘앤디’의 무죄를 입증할 기회를 얻지만, 노튼 소장은 ‘앤디’를 독방에 가두고 ‘토미’를 무참히 " +
                        "죽여버리는데..."));

        Movie 라라랜드 = movieRepository.save(new Movie("라라랜드", today.plusDays(3), today.plusDays(12), 60, G_RATED,
                "꿈을 꾸는 사람들을 위한 별들의 도시 ‘라라랜드’. 재즈 피아니스트 ‘세바스찬’(라이언 고슬링)과 배우 지망생 ‘미아’(엠마 스톤), 인생에서 가장 빛나는 순간 만난 두 사람은 미완성인 서로의 무대를 만들어가기 시작한다."));

        Movie 어바웃타임 = movieRepository.save(new Movie("어바웃타임", today.plusDays(4), today.plusDays(13), 60, G_RATED,
                "모태솔로 팀(돔놀 글리슨)은 성인이 된 날, 아버지(빌 나이)로부터 놀랄만한 가문의 비밀을 듣게 된다. 바로 시간을 되돌릴 수 있는 능력이 있다는 것! 그것이 비록 히틀러를 죽이거나 여신과 뜨거운 사랑을 할 수는 없지만, " +
                        "여자친구는 만들어 줄 순 있으리.. 꿈을 위해 런던으로 간 팀은 우연히 만난 사랑스러운 여인 메리에게 첫눈에 반하게 된다. 그녀의 사랑을 얻기 위해 자신의 특별한 능력을 마음껏 발휘하는 팀. 어설픈 대시, 어색한 웃음은 리와인드! " +
                        "뜨거웠던 밤은 더욱 뜨겁게 리플레이! 꿈에 그리던 그녀와 매일매일 최고의 순간을 보낸다. 하지만 그와 그녀의 사랑이 완벽해질수록 팀을 둘러싼 주변 상황들은 미묘하게 엇갈리고, 예상치 못한 사건들이 여기저기 나타나기 시작하는데… " +
                        "어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?"));

        // Screening
        // 상영종료
        LocalDateTime beforeDateTime = LocalDateTime.of(today.minusDays(8), LocalTime.of(13, 0));
        Screening screening1 = screeningRepository.save(new Screening(제1상영관, 범죄도시1, 10, beforeDateTime.plusHours(1)));
        Screening screening2 = screeningRepository.save(new Screening(제1상영관, 범죄도시2, beforeDateTime.plusHours(2)));
        Screening screening3 = screeningRepository.save(new Screening(제1상영관, 닥터_스트레인지, 10, beforeDateTime.plusHours(3)));
        Screening screening4 = screeningRepository.save(new Screening(제1상영관, 인터스텔라, beforeDateTime.plusHours(4)));
        Screening screening5 = screeningRepository.save(new Screening(제1상영관, 아이언맨, 10, beforeDateTime.plusHours(5)));
        Screening screening6 = screeningRepository.save(new Screening(제1상영관, 인크레더블_헐크, beforeDateTime.plusHours(6)));
        Screening screening7 = screeningRepository.save(new Screening(제1상영관, 닥터_스트레인지, 10, beforeDateTime.plusHours(7)));
        Screening screening8 = screeningRepository.save(new Screening(제1상영관, 인터스텔라, beforeDateTime.plusHours(8)));
        Screening screening9 = screeningRepository.save(new Screening(제1상영관, 인크레더블_헐크, 10, beforeDateTime.plusHours(9)));

        Screening screening10 = screeningRepository.save(new Screening(제2상영관, 범죄도시1, 10, beforeDateTime.plusHours(1)));
        Screening screening11 = screeningRepository.save(new Screening(제2상영관, 범죄도시2, beforeDateTime.plusHours(2)));
        Screening screening12 = screeningRepository.save(new Screening(제2상영관, 닥터_스트레인지, 10, beforeDateTime.plusHours(3)));
        Screening screening13 = screeningRepository.save(new Screening(제2상영관, 인터스텔라, beforeDateTime.plusHours(4)));
        Screening screening14 = screeningRepository.save(new Screening(제2상영관, 아이언맨, 10, beforeDateTime.plusHours(5)));
        Screening screening15 = screeningRepository.save(new Screening(제2상영관, 인크레더블_헐크, beforeDateTime.plusHours(6)));
        Screening screening16 = screeningRepository.save(new Screening(제2상영관, 닥터_스트레인지, 10, beforeDateTime.plusHours(7)));
        Screening screening17 = screeningRepository.save(new Screening(제2상영관, 인터스텔라, beforeDateTime.plusHours(8)));
        Screening screening18 = screeningRepository.save(new Screening(제2상영관, 인크레더블_헐크, 10, beforeDateTime.plusHours(9)));

        Screening screening19 = screeningRepository.save(new Screening(제3상영관, 범죄도시1, 10, beforeDateTime.plusHours(1)));
        Screening screening20 = screeningRepository.save(new Screening(제3상영관, 범죄도시2, beforeDateTime.plusHours(2)));
        Screening screening21 = screeningRepository.save(new Screening(제3상영관, 닥터_스트레인지, beforeDateTime.plusHours(3)));
        Screening screening22 = screeningRepository.save(new Screening(제3상영관, 인터스텔라, 10, beforeDateTime.plusHours(4)));
        Screening screening23 = screeningRepository.save(new Screening(제3상영관, 아이언맨, 10, beforeDateTime.plusHours(5)));
        Screening screening24 = screeningRepository.save(new Screening(제3상영관, 인크레더블_헐크, 10, beforeDateTime.plusHours(6)));
        Screening screening25 = screeningRepository.save(new Screening(제3상영관, 닥터_스트레인지, beforeDateTime.plusHours(7)));
        Screening screening26 = screeningRepository.save(new Screening(제3상영관, 인터스텔라, 10, beforeDateTime.plusHours(8)));
        Screening screening27 = screeningRepository.save(new Screening(제3상영관, 인크레더블_헐크, beforeDateTime.plusHours(9)));

        Screening screening28 = screeningRepository.save(new Screening(제4상영관, 범죄도시1, 10, beforeDateTime.plusHours(1)));
        Screening screening29 = screeningRepository.save(new Screening(제4상영관, 범죄도시2, beforeDateTime.plusHours(2)));
        Screening screening30 = screeningRepository.save(new Screening(제4상영관, 닥터_스트레인지, beforeDateTime.plusHours(3)));
        Screening screening31 = screeningRepository.save(new Screening(제4상영관, 인터스텔라, 10, beforeDateTime.plusHours(4)));
        Screening screening32 = screeningRepository.save(new Screening(제4상영관, 아이언맨, 10, beforeDateTime.plusHours(5)));
        Screening screening33 = screeningRepository.save(new Screening(제4상영관, 인크레더블_헐크, beforeDateTime.plusHours(6)));
        Screening screening34 = screeningRepository.save(new Screening(제4상영관, 닥터_스트레인지, 10, beforeDateTime.plusHours(7)));
        Screening screening35 = screeningRepository.save(new Screening(제4상영관, 인터스텔라, 10, beforeDateTime.plusHours(8)));
        Screening screening36 = screeningRepository.save(new Screening(제4상영관, 인크레더블_헐크, beforeDateTime.plusHours(9)));

        Screening screening37 = screeningRepository.save(new Screening(제5상영관, 범죄도시1, 10, beforeDateTime.plusHours(1)));
        Screening screening38 = screeningRepository.save(new Screening(제5상영관, 범죄도시2, beforeDateTime.plusHours(2)));
        Screening screening39 = screeningRepository.save(new Screening(제5상영관, 닥터_스트레인지, 10, beforeDateTime.plusHours(3)));
        Screening screening40 = screeningRepository.save(new Screening(제5상영관, 인터스텔라, 10, beforeDateTime.plusHours(4)));
        Screening screening41 = screeningRepository.save(new Screening(제5상영관, 아이언맨, beforeDateTime.plusHours(5)));
        Screening screening42 = screeningRepository.save(new Screening(제5상영관, 인크레더블_헐크, 10, beforeDateTime.plusHours(6)));
        Screening screening43 = screeningRepository.save(new Screening(제5상영관, 닥터_스트레인지, beforeDateTime.plusHours(7)));
        Screening screening44 = screeningRepository.save(new Screening(제5상영관, 인터스텔라, beforeDateTime.plusHours(8)));
        Screening screening45 = screeningRepository.save(new Screening(제5상영관, 인크레더블_헐크, 10, beforeDateTime.plusHours(9)));


        // 상영중
        LocalDateTime firstDateTime = LocalDateTime.of(today, LocalTime.now().withMinute(0).withSecond(0).withNano(0));
        LocalDateTime beforeDateTime1 = LocalDateTime.of(today.minusDays(1), LocalTime.now().withMinute(0).withSecond(0).withNano(0));
        LocalDateTime beforeDateTime2 = LocalDateTime.of(today.minusDays(2), LocalTime.now().withMinute(0).withSecond(0).withNano(0));
        LocalDateTime beforeDateTime3 = LocalDateTime.of(today.minusDays(3), LocalTime.now().withMinute(0).withSecond(0).withNano(0));
        Screening screening46 = screeningRepository.save(new Screening(제1상영관, 토르_천둥의_신, 20, firstDateTime.minusHours(1)));
        Screening screening47 = screeningRepository.save(new Screening(제1상영관, 아이언맨3, 10, firstDateTime.plusHours(2)));
        Screening screening48 = screeningRepository.save(new Screening(제1상영관, 토르_다크_월드, 10, beforeDateTime2.plusHours(3)));
        Screening screening49 = screeningRepository.save(new Screening(제1상영관, 어벤져스, 10, beforeDateTime3.plusHours(4)));
        Screening screening50 = screeningRepository.save(new Screening(제1상영관, 타이타닉, firstDateTime.plusHours(5)));
        Screening screening51 = screeningRepository.save(new Screening(제1상영관, 인셉션, 20, beforeDateTime1.plusHours(6)));
        Screening screening52 = screeningRepository.save(new Screening(제1상영관, 올드보이, 10, beforeDateTime2.plusHours(7)));
        Screening screening53 = screeningRepository.save(new Screening(제1상영관, 토르_천둥의_신, 20, beforeDateTime3.plusHours(8)));
        Screening screening54 = screeningRepository.save(new Screening(제1상영관, 아이언맨3, firstDateTime.plusHours(9)));

        Screening screening55 = screeningRepository.save(new Screening(제2상영관, 토르_다크_월드, 10, firstDateTime.plusHours(1)));
        Screening screening56 = screeningRepository.save(new Screening(제2상영관, 인셉션, firstDateTime.plusHours(2)));
        Screening screening57 = screeningRepository.save(new Screening(제2상영관, 토르_다크_월드, firstDateTime.plusHours(3)));
        Screening screening58 = screeningRepository.save(new Screening(제2상영관, 아이언맨3, 10, beforeDateTime2.plusHours(4)));
        Screening screening59 = screeningRepository.save(new Screening(제2상영관, 올드보이, firstDateTime.plusHours(5)));
        Screening screening60 = screeningRepository.save(new Screening(제2상영관, 인셉션, firstDateTime.plusHours(6)));
        Screening screening61 = screeningRepository.save(new Screening(제2상영관, 인셉션, 30, firstDateTime.plusHours(7)));
        Screening screening62 = screeningRepository.save(new Screening(제2상영관, 토르_다크_월드, firstDateTime.plusHours(8)));
        Screening screening63 = screeningRepository.save(new Screening(제2상영관, 아이언맨3, firstDateTime.plusHours(9)));

        Screening screening64 = screeningRepository.save(new Screening(제3상영관, 인셉션, firstDateTime.plusHours(1)));
        Screening screening65 = screeningRepository.save(new Screening(제3상영관, 아이언맨3, 10, beforeDateTime1.plusHours(2)));
        Screening screening66 = screeningRepository.save(new Screening(제3상영관, 토르_다크_월드, 10, firstDateTime.plusHours(3)));
        Screening screening67 = screeningRepository.save(new Screening(제3상영관, 어벤져스, firstDateTime.plusHours(4)));
        Screening screening68 = screeningRepository.save(new Screening(제3상영관, 타이타닉, firstDateTime.plusHours(5)));
        Screening screening69 = screeningRepository.save(new Screening(제3상영관, 인셉션, firstDateTime.plusHours(6)));
        Screening screening70 = screeningRepository.save(new Screening(제3상영관, 올드보이, firstDateTime.plusHours(7)));
        Screening screening71 = screeningRepository.save(new Screening(제3상영관, 토르_천둥의_신, firstDateTime.plusHours(8)));
        Screening screening72 = screeningRepository.save(new Screening(제3상영관, 아이언맨3, firstDateTime.plusHours(9)));

        Screening screening73 = screeningRepository.save(new Screening(제4상영관, 올드보이, firstDateTime.plusHours(1)));
        Screening screening74 = screeningRepository.save(new Screening(제4상영관, 아이언맨3, firstDateTime.plusHours(2)));
        Screening screening75 = screeningRepository.save(new Screening(제4상영관, 토르_다크_월드, 10, firstDateTime.minusHours(3)));
        Screening screening76 = screeningRepository.save(new Screening(제4상영관, 타이타닉, firstDateTime.plusHours(4)));
        Screening screening77 = screeningRepository.save(new Screening(제4상영관, 타이타닉, 10, firstDateTime.plusHours(5)));
        Screening screening78 = screeningRepository.save(new Screening(제4상영관, 인셉션, firstDateTime.plusHours(6)));
        Screening screening79 = screeningRepository.save(new Screening(제4상영관, 올드보이, firstDateTime.plusHours(7)));
        Screening screening80 = screeningRepository.save(new Screening(제4상영관, 토르_천둥의_신, 10, beforeDateTime2.plusHours(8)));
        Screening screening81 = screeningRepository.save(new Screening(제4상영관, 아이언맨3, 10, beforeDateTime3.plusHours(9)));

        Screening screening82 = screeningRepository.save(new Screening(제5상영관, 토르_천둥의_신, firstDateTime.plusHours(1)));
        Screening screening83 = screeningRepository.save(new Screening(제5상영관, 아이언맨3, firstDateTime.plusHours(2)));
        Screening screening84 = screeningRepository.save(new Screening(제5상영관, 인셉션, 10, firstDateTime.plusHours(3)));
        Screening screening85 = screeningRepository.save(new Screening(제5상영관, 어벤져스, firstDateTime.plusHours(4)));
        Screening screening86 = screeningRepository.save(new Screening(제5상영관, 아이언맨3, firstDateTime.plusHours(5)));
        Screening screening87 = screeningRepository.save(new Screening(제5상영관, 아이언맨3, firstDateTime.plusHours(6)));
        Screening screening88 = screeningRepository.save(new Screening(제5상영관, 올드보이, 10, firstDateTime.plusHours(7)));
        Screening screening89 = screeningRepository.save(new Screening(제5상영관, 토르_다크_월드, 10, beforeDateTime1.plusHours(8)));
        Screening screening90 = screeningRepository.save(new Screening(제5상영관, 아이언맨3, 10, beforeDateTime3.plusHours(9)));

        //상영예정
        LocalDateTime fifthDateTime = LocalDateTime.of(today.plusDays(5), LocalTime.of(13, 0));
        Screening screening91 = screeningRepository.save(new Screening(제1상영관, 다크나이트, 30, fifthDateTime.plusHours(1)));
        Screening screening92 = screeningRepository.save(new Screening(제1상영관, 세_얼간이, fifthDateTime.plusHours(2)));
        Screening screening93 = screeningRepository.save(new Screening(제1상영관, 기생충, 10, fifthDateTime.plusHours(3)));
        Screening screening94 = screeningRepository.save(new Screening(제1상영관, 아바타, fifthDateTime.plusHours(4)));
        Screening screening95 = screeningRepository.save(new Screening(제1상영관, 쇼생크_탈출, 10, fifthDateTime.plusHours(5)));
        Screening screening96 = screeningRepository.save(new Screening(제1상영관, 라라랜드, fifthDateTime.plusHours(6)));
        Screening screening97 = screeningRepository.save(new Screening(제1상영관, 어바웃타임, 20, fifthDateTime.plusHours(7)));
        Screening screening98 = screeningRepository.save(new Screening(제1상영관, 기생충, 10, fifthDateTime.plusHours(8)));
        Screening screening99 = screeningRepository.save(new Screening(제1상영관, 다크나이트, 20, fifthDateTime.plusHours(9)));

        Screening screening100 = screeningRepository.save(new Screening(제2상영관, 다크나이트, fifthDateTime.plusHours(1)));
        Screening screening101 = screeningRepository.save(new Screening(제2상영관, 세_얼간이, 10, fifthDateTime.plusHours(2)));
        Screening screening102 = screeningRepository.save(new Screening(제2상영관, 기생충, 10, fifthDateTime.plusHours(3)));
        Screening screening103 = screeningRepository.save(new Screening(제2상영관, 아바타, 10, fifthDateTime.plusHours(4)));
        Screening screening104 = screeningRepository.save(new Screening(제2상영관, 쇼생크_탈출, fifthDateTime.plusHours(5)));
        Screening screening105 = screeningRepository.save(new Screening(제2상영관, 라라랜드, fifthDateTime.plusHours(6)));
        Screening screening106 = screeningRepository.save(new Screening(제2상영관, 어바웃타임, fifthDateTime.plusHours(7)));
        Screening screening107 = screeningRepository.save(new Screening(제2상영관, 기생충, fifthDateTime.plusHours(8)));
        Screening screening108 = screeningRepository.save(new Screening(제2상영관, 다크나이트, fifthDateTime.plusHours(9)));

        Screening screening109 = screeningRepository.save(new Screening(제3상영관, 다크나이트, 10, fifthDateTime.plusHours(1)));
        Screening screening110 = screeningRepository.save(new Screening(제3상영관, 세_얼간이, fifthDateTime.plusHours(2)));
        Screening screening111 = screeningRepository.save(new Screening(제3상영관, 기생충, 40, fifthDateTime.plusHours(3)));
        Screening screening112 = screeningRepository.save(new Screening(제3상영관, 아바타, fifthDateTime.plusHours(4)));
        Screening screening113 = screeningRepository.save(new Screening(제3상영관, 쇼생크_탈출, fifthDateTime.plusHours(5)));
        Screening screening114 = screeningRepository.save(new Screening(제3상영관, 라라랜드, fifthDateTime.plusHours(6)));
        Screening screening115 = screeningRepository.save(new Screening(제3상영관, 어바웃타임, fifthDateTime.plusHours(7)));
        Screening screening116 = screeningRepository.save(new Screening(제3상영관, 기생충, fifthDateTime.plusHours(8)));
        Screening screening117 = screeningRepository.save(new Screening(제3상영관, 다크나이트, fifthDateTime.plusHours(9)));

        Screening screening118 = screeningRepository.save(new Screening(제4상영관, 다크나이트, fifthDateTime.plusHours(1)));
        Screening screening119 = screeningRepository.save(new Screening(제4상영관, 세_얼간이, fifthDateTime.plusHours(2)));
        Screening screening120 = screeningRepository.save(new Screening(제4상영관, 기생충, 10, fifthDateTime.plusHours(3)));
        Screening screening121 = screeningRepository.save(new Screening(제4상영관, 아바타, fifthDateTime.plusHours(4)));
        Screening screening122 = screeningRepository.save(new Screening(제4상영관, 쇼생크_탈출, fifthDateTime.plusHours(5)));
        Screening screening123 = screeningRepository.save(new Screening(제4상영관, 라라랜드, fifthDateTime.plusHours(6)));
        Screening screening124 = screeningRepository.save(new Screening(제4상영관, 어바웃타임, 10, fifthDateTime.plusHours(7)));
        Screening screening125 = screeningRepository.save(new Screening(제4상영관, 기생충, 10, fifthDateTime.plusHours(8)));
        Screening screening126 = screeningRepository.save(new Screening(제4상영관, 다크나이트, fifthDateTime.plusHours(9)));

        Screening screening127 = screeningRepository.save(new Screening(제5상영관, 다크나이트, fifthDateTime.plusHours(1)));
        Screening screening128 = screeningRepository.save(new Screening(제5상영관, 세_얼간이, fifthDateTime.plusHours(2)));
        Screening screening129 = screeningRepository.save(new Screening(제5상영관, 기생충, fifthDateTime.plusHours(3)));
        Screening screening130 = screeningRepository.save(new Screening(제5상영관, 아바타, fifthDateTime.plusHours(4)));
        Screening screening131 = screeningRepository.save(new Screening(제5상영관, 쇼생크_탈출, 10, fifthDateTime.plusHours(5)));
        Screening screening132 = screeningRepository.save(new Screening(제5상영관, 라라랜드, fifthDateTime.plusHours(6)));
        Screening screening133 = screeningRepository.save(new Screening(제5상영관, 어바웃타임, 20, fifthDateTime.plusHours(7)));
        Screening screening134 = screeningRepository.save(new Screening(제5상영관, 기생충, fifthDateTime.plusHours(8)));
        Screening screening135 = screeningRepository.save(new Screening(제5상영관, 다크나이트, 10, fifthDateTime.plusHours(9)));

        // Reservation
        // 상영 종료
        reservationRepository.save(new Reservation(10, false, 김철수, screening1));
        reservationRepository.save(new Reservation(5, false, 김철수, screening14));
        reservationRepository.save(new Reservation(6, false, 김철수, screening25));
        reservationRepository.save(new Reservation(2, false, 김철수, screening29));
        reservationRepository.save(new Reservation(1, false, 김철수, screening37));
        reservationRepository.save(new Reservation(4, true, 김철수, screening11));
        reservationRepository.save(new Reservation(3, true, 김철수, screening13));
        reservationRepository.save(new Reservation(8, true, 김철수, screening15));
        reservationRepository.save(new Reservation(9, true, 김철수, screening17));
        reservationRepository.save(new Reservation(7, true, 김철수, screening20));

        reservationRepository.save(new Reservation(1, false, 김짱구, screening10));
        reservationRepository.save(new Reservation(2, false, 김훈이, screening12));
        reservationRepository.save(new Reservation(3, false, 김맹구, screening14));
        reservationRepository.save(new Reservation(4, false, 김영희, screening16));
        reservationRepository.save(new Reservation(5, false, 김유리, screening18));
        reservationRepository.save(new Reservation(6, false, 김수지, screening19));
        reservationRepository.save(new Reservation(7, false, 나미리, screening22));
        reservationRepository.save(new Reservation(8, false, 신형만, screening23));
        reservationRepository.save(new Reservation(9, false, 봉미선, screening24));
        reservationRepository.save(new Reservation(10, false, 김짱구, screening26));
        reservationRepository.save(new Reservation(9, false, 김훈이, screening28));
        reservationRepository.save(new Reservation(8, false, 김맹구, screening31));
        reservationRepository.save(new Reservation(7, false, 김영희, screening32));
        reservationRepository.save(new Reservation(6, false, 김유리, screening34));
        reservationRepository.save(new Reservation(5, false, 김수지, screening35));
        reservationRepository.save(new Reservation(4, false, 나미리, screening37));
        reservationRepository.save(new Reservation(3, false, 신형만, screening39));
        reservationRepository.save(new Reservation(2, false, 봉미선, screening40));
        reservationRepository.save(new Reservation(1, false, 나미리, screening42));
        reservationRepository.save(new Reservation(2, false, 신형만, screening45));

        // 상영중
        reservationRepository.save(new Reservation(7, false, 김철수, screening46));
        reservationRepository.save(new Reservation(4, false, 김철수, screening66));
        reservationRepository.save(new Reservation(5, false, 김철수, screening75));
        reservationRepository.save(new Reservation(3, false, 김철수, screening80));
        reservationRepository.save(new Reservation(1, false, 김철수, screening89));
        reservationRepository.save(new Reservation(8, true, 김철수, screening65));
        reservationRepository.save(new Reservation(9, true, 김철수, screening77));
        reservationRepository.save(new Reservation(10, true, 김철수, screening81));
        reservationRepository.save(new Reservation(9, true, 김철수, screening84));
        reservationRepository.save(new Reservation(7, true, 김철수, screening90));

        reservationRepository.save(new Reservation(1, false, 김짱구, screening46));
        reservationRepository.save(new Reservation(2, false, 김훈이, screening49));
        reservationRepository.save(new Reservation(6, false, 김맹구, screening52));
        reservationRepository.save(new Reservation(7, false, 김영희, screening55));
        reservationRepository.save(new Reservation(1, false, 김유리, screening58));
        reservationRepository.save(new Reservation(5, false, 김수지, screening65));
        reservationRepository.save(new Reservation(9, false, 나미리, screening77));
        reservationRepository.save(new Reservation(9, false, 신형만, screening81));
        reservationRepository.save(new Reservation(2, false, 봉미선, screening84));
        reservationRepository.save(new Reservation(3, false, 김짱구, screening90));
        reservationRepository.save(new Reservation(4, false, 김훈이, screening47));
        reservationRepository.save(new Reservation(8, false, 김맹구, screening48));
        reservationRepository.save(new Reservation(4, false, 김영희, screening51));
        reservationRepository.save(new Reservation(5, false, 김유리, screening51));
        reservationRepository.save(new Reservation(1, false, 김수지, screening53));
        reservationRepository.save(new Reservation(1, false, 나미리, screening53));
        reservationRepository.save(new Reservation(2, false, 신형만, screening61));
        reservationRepository.save(new Reservation(3, false, 봉미선, screening61));
        reservationRepository.save(new Reservation(6, false, 나미리, screening61));
        reservationRepository.save(new Reservation(6, false, 신형만, screening88));

        // 상영 예정
        reservationRepository.save(new Reservation(2, false, 김철수, screening91));
        reservationRepository.save(new Reservation(2, false, 김철수, screening124));
        reservationRepository.save(new Reservation(5, false, 김철수, screening103));
        reservationRepository.save(new Reservation(4, false, 김철수, screening135));
        reservationRepository.save(new Reservation(9, false, 김철수, screening101));
        reservationRepository.save(new Reservation(10, true, 김철수, screening100));
        reservationRepository.save(new Reservation(2, true, 김철수, screening102));
        reservationRepository.save(new Reservation(1, true, 김철수, screening105));
        reservationRepository.save(new Reservation(5, true, 김철수, screening120));
        reservationRepository.save(new Reservation(7, true, 김철수, screening130));

        reservationRepository.save(new Reservation(1, false, 김짱구, screening91));
        reservationRepository.save(new Reservation(4, false, 김훈이, screening93));
        reservationRepository.save(new Reservation(6, false, 김맹구, screening95));
        reservationRepository.save(new Reservation(3, false, 김영희, screening97));
        reservationRepository.save(new Reservation(2, false, 김유리, screening99));
        reservationRepository.save(new Reservation(5, false, 김수지, screening109));
        reservationRepository.save(new Reservation(7, false, 나미리, screening111));
        reservationRepository.save(new Reservation(8, false, 신형만, screening131));
        reservationRepository.save(new Reservation(9, false, 봉미선, screening120));
        reservationRepository.save(new Reservation(1, false, 김짱구, screening111));
        reservationRepository.save(new Reservation(2, false, 김훈이, screening111));
        reservationRepository.save(new Reservation(6, false, 김맹구, screening125));
        reservationRepository.save(new Reservation(2, false, 김영희, screening102));
        reservationRepository.save(new Reservation(4, false, 김유리, screening99));
        reservationRepository.save(new Reservation(7, false, 김수지, screening98));
        reservationRepository.save(new Reservation(5, false, 나미리, screening97));
        reservationRepository.save(new Reservation(5, false, 신형만, screening91));
        reservationRepository.save(new Reservation(2, false, 봉미선, screening133));
        reservationRepository.save(new Reservation(3, false, 나미리, screening133));
        reservationRepository.save(new Reservation(2, false, 신형만, screening111));

        // Actor
        Actor 마동석 = actorRepository.save(new Actor("마동석"));
        Actor 박지환 = actorRepository.save(new Actor("박지환"));
        Actor 윤계상 = actorRepository.save(new Actor("윤계상"));
        Actor 손석구 = actorRepository.save(new Actor("손석구"));
        Actor 베네딕트_컴버비치 = actorRepository.save(new Actor("베네딕트 컴버비치"));
        Actor 레이첼_맥아담스 = actorRepository.save(new Actor("레이첼 맥아담스"));
        Actor 브루스_컴벨 = actorRepository.save(new Actor("브루스 컴벨"));
        Actor 매슈_매커니히 = actorRepository.save(new Actor("매슈 매커니히"));
        Actor 앤_해서웨이 = actorRepository.save(new Actor("앤 해서웨이"));
        Actor 제시카_채스테인 = actorRepository.save(new Actor("제시카 채스테인"));
        Actor 로버트_다우니_주니어 = actorRepository.save(new Actor("로버트 다우니 주니어"));
        Actor 존_파브로 = actorRepository.save(new Actor("존 파브로"));
        Actor 귀네스_팰트로 = actorRepository.save(new Actor("귀네스 팰트로"));
        Actor 마크_러펄로 = actorRepository.save(new Actor("마크 러펄로"));
        Actor 애드워드_노턴 = actorRepository.save(new Actor("애드워드 노턴"));
        Actor 에릭_바나 = actorRepository.save(new Actor("에릭 바나"));
        Actor 크리스_헴스워스 = actorRepository.save(new Actor("크리스 헴스워스"));
        Actor 내털리_포트먼 = actorRepository.save(new Actor("내털리 포트먼"));
        Actor 톰_히들스턴 = actorRepository.save(new Actor("톰 히들스턴"));
        Actor 벤_킹즐리 = actorRepository.save(new Actor("벤 킹즐리"));
        Actor 레오나르도_디카프리오 = actorRepository.save(new Actor("레오나르도 디카프리오"));
        Actor 케이트_윈슬렛 = actorRepository.save(new Actor("케이트 윈슬렛"));
        Actor 빌리_제인 = actorRepository.save(new Actor("빌리 제인"));
        Actor 조셉_고든_레빗 = actorRepository.save(new Actor("조셉 고든 레빗"));
        Actor 톰_하디 = actorRepository.save(new Actor("톰 하디"));
        Actor 최민식 = actorRepository.save(new Actor("최민식"));
        Actor 강혜정 = actorRepository.save(new Actor("강혜정"));
        Actor 유지태 = actorRepository.save(new Actor("유지태"));
        Actor 크리스찬_베일 = actorRepository.save(new Actor("크리스찬 베일"));
        Actor 히스_레저 = actorRepository.save(new Actor("히스 레저"));
        Actor 게리_올드먼 = actorRepository.save(new Actor("게리 올드먼"));
        Actor 아미르_칸 = actorRepository.save(new Actor("아미르 칸"));
        Actor 샤르만_조시 = actorRepository.save(new Actor("샤르만 조시"));
        Actor 오미_베이디아 = actorRepository.save(new Actor("오미 베이디아"));
        Actor 송강호 = actorRepository.save(new Actor("송강호"));
        Actor 최우식 = actorRepository.save(new Actor("최우식"));
        Actor 이선균 = actorRepository.save(new Actor("이선균"));
        Actor 샘_워딩턴 = actorRepository.save(new Actor("샘 워딩턴"));
        Actor 조이_살다나 = actorRepository.save(new Actor("조이 살다나"));
        Actor 시고니_위버 = actorRepository.save(new Actor("시고니 위버"));
        Actor 모건_프리먼 = actorRepository.save(new Actor("모건 프리먼"));
        Actor 팀_로빈스 = actorRepository.save(new Actor("팀 로빈스"));
        Actor 밥_건턴 = actorRepository.save(new Actor("밥 건턴"));
        Actor 라이언_고슬링 = actorRepository.save(new Actor("라이언 고슬링"));
        Actor 엠마_스톤 = actorRepository.save(new Actor("엠마 스톤"));
        Actor 존_레전드 = actorRepository.save(new Actor("존 레전드"));
        Actor 도널_글리슨 = actorRepository.save(new Actor("도널 글리슨"));
        Actor 빌_나이 = actorRepository.save(new Actor("빌 나이"));
        movieActorRepository.save(new MovieActor(범죄도시1, 마동석));
        movieActorRepository.save(new MovieActor(범죄도시1, 박지환));
        movieActorRepository.save(new MovieActor(범죄도시1, 윤계상));
        movieActorRepository.save(new MovieActor(범죄도시2, 마동석));
        movieActorRepository.save(new MovieActor(범죄도시2, 박지환));
        movieActorRepository.save(new MovieActor(범죄도시2, 손석구));
        movieActorRepository.save(new MovieActor(닥터_스트레인지, 베네딕트_컴버비치));
        movieActorRepository.save(new MovieActor(닥터_스트레인지, 레이첼_맥아담스));
        movieActorRepository.save(new MovieActor(닥터_스트레인지, 브루스_컴벨));
        movieActorRepository.save(new MovieActor(인터스텔라, 매슈_매커니히));
        movieActorRepository.save(new MovieActor(인터스텔라, 앤_해서웨이));
        movieActorRepository.save(new MovieActor(인터스텔라, 제시카_채스테인));
        movieActorRepository.save(new MovieActor(아이언맨, 로버트_다우니_주니어));
        movieActorRepository.save(new MovieActor(아이언맨, 존_파브로));
        movieActorRepository.save(new MovieActor(아이언맨, 귀네스_팰트로));
        movieActorRepository.save(new MovieActor(인크레더블_헐크, 마크_러펄로));
        movieActorRepository.save(new MovieActor(인크레더블_헐크, 애드워드_노턴));
        movieActorRepository.save(new MovieActor(인크레더블_헐크, 에릭_바나));
        movieActorRepository.save(new MovieActor(토르_천둥의_신, 크리스_헴스워스));
        movieActorRepository.save(new MovieActor(토르_천둥의_신, 내털리_포트먼));
        movieActorRepository.save(new MovieActor(토르_천둥의_신, 톰_히들스턴));
        movieActorRepository.save(new MovieActor(아이언맨3, 로버트_다우니_주니어));
        movieActorRepository.save(new MovieActor(아이언맨3, 귀네스_팰트로));
        movieActorRepository.save(new MovieActor(아이언맨3, 벤_킹즐리));
        movieActorRepository.save(new MovieActor(토르_다크_월드, 크리스_헴스워스));
        movieActorRepository.save(new MovieActor(토르_다크_월드, 내털리_포트먼));
        movieActorRepository.save(new MovieActor(토르_다크_월드, 톰_히들스턴));
        movieActorRepository.save(new MovieActor(어벤져스, 로버트_다우니_주니어));
        movieActorRepository.save(new MovieActor(어벤져스, 크리스_헴스워스));
        movieActorRepository.save(new MovieActor(어벤져스, 마크_러펄로));
        movieActorRepository.save(new MovieActor(타이타닉, 레오나르도_디카프리오));
        movieActorRepository.save(new MovieActor(타이타닉, 케이트_윈슬렛));
        movieActorRepository.save(new MovieActor(타이타닉, 빌리_제인));
        movieActorRepository.save(new MovieActor(인셉션, 조셉_고든_레빗));
        movieActorRepository.save(new MovieActor(인셉션, 톰_하디));
        movieActorRepository.save(new MovieActor(인셉션, 레오나르도_디카프리오));
        movieActorRepository.save(new MovieActor(올드보이, 최민식));
        movieActorRepository.save(new MovieActor(올드보이, 강혜정));
        movieActorRepository.save(new MovieActor(올드보이, 유지태));
        movieActorRepository.save(new MovieActor(다크나이트, 크리스찬_베일));
        movieActorRepository.save(new MovieActor(다크나이트, 히스_레저));
        movieActorRepository.save(new MovieActor(다크나이트, 게리_올드먼));
        movieActorRepository.save(new MovieActor(세_얼간이, 아미르_칸));
        movieActorRepository.save(new MovieActor(세_얼간이, 샤르만_조시));
        movieActorRepository.save(new MovieActor(세_얼간이, 오미_베이디아));
        movieActorRepository.save(new MovieActor(기생충, 송강호));
        movieActorRepository.save(new MovieActor(기생충, 최우식));
        movieActorRepository.save(new MovieActor(기생충, 이선균));
        movieActorRepository.save(new MovieActor(아바타, 샘_워딩턴));
        movieActorRepository.save(new MovieActor(아바타, 조이_살다나));
        movieActorRepository.save(new MovieActor(아바타, 시고니_위버));
        movieActorRepository.save(new MovieActor(쇼생크_탈출, 모건_프리먼));
        movieActorRepository.save(new MovieActor(쇼생크_탈출, 팀_로빈스));
        movieActorRepository.save(new MovieActor(쇼생크_탈출, 밥_건턴));
        movieActorRepository.save(new MovieActor(라라랜드, 라이언_고슬링));
        movieActorRepository.save(new MovieActor(라라랜드, 엠마_스톤));
        movieActorRepository.save(new MovieActor(라라랜드, 존_레전드));
        movieActorRepository.save(new MovieActor(어바웃타임, 도널_글리슨));
        movieActorRepository.save(new MovieActor(어바웃타임, 레이첼_맥아담스));
        movieActorRepository.save(new MovieActor(어바웃타임, 빌_나이));

        // Director
        Director 강윤성 = directorRepository.save(new Director("강윤성"));
        Director 이상용 = directorRepository.save(new Director("이상용"));
        Director 샘_레이미 = directorRepository.save(new Director("샘 레이미"));
        Director 크리스토퍼_놀란 = directorRepository.save(new Director("크리스토퍼 놀란"));
        Director 감독_존_파브로 = directorRepository.save(new Director("존 파브로"));
        Director 루이_르테리에 = directorRepository.save(new Director("루이 르테리에"));
        Director 케네스_브래나 = directorRepository.save(new Director("케네스 브래나"));
        Director 셰인_블랙 = directorRepository.save(new Director("셰인 블랙"));
        Director 앨런_테일러 = directorRepository.save(new Director("앨런 테일러"));
        Director 앤소니_루소 = directorRepository.save(new Director("앤소니 루소"));
        Director 존_루소 = directorRepository.save(new Director("존 루소"));
        Director 제임스_카메론 = directorRepository.save(new Director("제임스 카메론"));
        Director 박찬욱 = directorRepository.save(new Director("박찬욱"));
        Director 라지쿠마르_히라니 = directorRepository.save(new Director("라지쿠마르 히라니"));
        Director 봉준호 = directorRepository.save(new Director("봉준호"));
        Director 프랭크_다라본트 = directorRepository.save(new Director("프랭크 다라본트"));
        Director 데이미언_셔젤 = directorRepository.save(new Director("데이미언 셔젤"));
        Director 리처드_커티스 = directorRepository.save(new Director("리처드 커티스"));
        movieDirectorRepository.save(new MovieDirector(범죄도시1, 강윤성));
        movieDirectorRepository.save(new MovieDirector(범죄도시2, 이상용));
        movieDirectorRepository.save(new MovieDirector(닥터_스트레인지, 샘_레이미));
        movieDirectorRepository.save(new MovieDirector(인터스텔라, 크리스토퍼_놀란));
        movieDirectorRepository.save(new MovieDirector(아이언맨, 감독_존_파브로));
        movieDirectorRepository.save(new MovieDirector(인크레더블_헐크, 루이_르테리에));
        movieDirectorRepository.save(new MovieDirector(토르_천둥의_신, 케네스_브래나));
        movieDirectorRepository.save(new MovieDirector(아이언맨3, 셰인_블랙));
        movieDirectorRepository.save(new MovieDirector(토르_다크_월드, 앨런_테일러));
        movieDirectorRepository.save(new MovieDirector(어벤져스, 앤소니_루소));
        movieDirectorRepository.save(new MovieDirector(어벤져스, 존_루소));

        movieDirectorRepository.save(new MovieDirector(타이타닉, 제임스_카메론));
        movieDirectorRepository.save(new MovieDirector(인셉션, 크리스토퍼_놀란));
        movieDirectorRepository.save(new MovieDirector(올드보이, 박찬욱));
        movieDirectorRepository.save(new MovieDirector(다크나이트, 크리스토퍼_놀란));
        movieDirectorRepository.save(new MovieDirector(세_얼간이, 라지쿠마르_히라니));
        movieDirectorRepository.save(new MovieDirector(기생충, 봉준호));
        movieDirectorRepository.save(new MovieDirector(아바타, 제임스_카메론));
        movieDirectorRepository.save(new MovieDirector(쇼생크_탈출, 프랭크_다라본트));
        movieDirectorRepository.save(new MovieDirector(라라랜드, 데이미언_셔젤));
        movieDirectorRepository.save(new MovieDirector(어바웃타임, 리처드_커티스));
        screeningRepository.findAllByStartAtGreaterThanEqual(LocalDateTime.now())
                .forEach(Screening::initAudienceCount);
    }
}
