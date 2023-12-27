package common.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("storeOrderCommonAPIServ")
public class StoreOrderCommonAPI_Service implements I_StoreOrderCommonAPI_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreRegisterService.class);

	@Autowired
	private WebClient webClient;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Float> getQohByResourceLocations(Long rSeqNo, CopyOnWriteArrayList<Long> llist) 
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float qoh = (float) 0;
			Mono<Float> qohMono = null;

			// Flux.fromIterable(llist), Long.class).retrieve()
			// Mono.just(products), new ParameterizedTypeReference<List<Product>>() {}

			qohMono = webClient.method(HttpMethod.GET)
					.uri("/resourceLocationPublicReadManagement/getQohByResourceLocations/"+rSeqNo)
					.body(Mono.just(llist), new ParameterizedTypeReference<CopyOnWriteArrayList<Long>>() {
					}).retrieve().bodyToMono(Float.class);
			qoh = qohMono.block();
			return qoh;
		}, asyncExecutor);
		return future;
	}

	
	public CompletableFuture<Float> getQohByAllResourceLocations(Long rSeqNo) 
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float qoh = (float) 0;
			Mono<Float> qohMono = null;

			// Flux.fromIterable(llist), Long.class).retrieve()
			// Mono.just(products), new ParameterizedTypeReference<List<Product>>() {}

			qohMono = webClient.method(HttpMethod.GET)
					.uri("/resourceLocationPublicReadManagement/getQohByAllResourceLocations/"+rSeqNo)
					.body(Mono.just(rSeqNo), Long.class).retrieve().bodyToMono(Float.class);
			qoh = qohMono.block();
			return qoh;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updQohByResourceLocation(Long rSeqNo, Long locSeqNo, Float qoh) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {			

			// Flux.fromIterable(llist), Long.class).retrieve()
			// Mono.just(products), new ParameterizedTypeReference<List<Product>>() {}

			webClient.method(HttpMethod.GET)
					.uri("/resourceLocationPublicCUDManagement/setPartyLocationResourceQoh/"+rSeqNo+"/"+qoh+"/"+locSeqNo)
					.body(Mono.just(rSeqNo), Long.class).retrieve().bodyToMono(Float.class).block();
			return;
		}, asyncExecutor);
		return future;
	}

	
}