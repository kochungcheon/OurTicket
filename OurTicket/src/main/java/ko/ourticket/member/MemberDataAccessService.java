package ko.ourticket.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDataAccessService implements MemberReader {
	private final MemberRepository memberRepository;

	public Member findMemberBy(String nickName) {
		return memberRepository.findByNickName(nickName)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다 닉네임 : " + nickName));
	}
}
