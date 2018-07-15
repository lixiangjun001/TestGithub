package consumer.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="consul-provider2",fallback=TestRepositoryImpl.class)
public interface TestRepository {
	@RequestMapping(value="/consul/show")
	public String show();
}
