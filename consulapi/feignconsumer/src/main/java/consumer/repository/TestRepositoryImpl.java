package consumer.repository;

import org.springframework.stereotype.Component;

@Component
public class TestRepositoryImpl implements TestRepository {

	@Override
	public String show() {
		return "调用失败";
	}
}
