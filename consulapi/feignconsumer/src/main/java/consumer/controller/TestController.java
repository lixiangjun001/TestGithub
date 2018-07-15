package consumer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import consumer.repository.TestRepository;

@RestController
@RequestMapping("/feign")
public class TestController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient clinet;
	
	@Autowired
	private TestRepository testRepository;
	
	
	@RequestMapping("/testRepository")
	@HystrixCommand(fallbackMethod="teachfallback")
	public String teach() {
		return testRepository.show();
	}
	
	public String teachfallback() {
		return "调用失败";
	}
	
	@RequestMapping("/discovery")
	public String discoveryservice() {
		//若注册的服务挂掉,则注册到该服务上的节点获取不到
		List<ServiceInstance> list =  discoveryClient.getInstances("consul-provider2");//获取所有服务
		System.out.println("服务个数:"+list.size());
		List<Object> name = new ArrayList<>();
		for (ServiceInstance serviceInstance : list) {
			System.out.println(serviceInstance.getServiceId()+":"+serviceInstance.getPort());
			name.add(serviceInstance.getServiceId());
		}
		return "发现正常";
	}
	//轮询
	@RequestMapping("/load")
	public Integer loadservice() {
		ServiceInstance serviceInstance= clinet.choose("consul-provider2");//查询单个服务
	    return serviceInstance.getPort();
	}

}
