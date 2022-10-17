package com.whereeco.domain.youtubeurl.service;

import com.whereeco.domain.youtubeurl.entity.YoutubeUrl;
import com.whereeco.domain.youtubeurl.repository.YoutubeUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoutubeUrlService {

    private final YoutubeUrlRepository youtubeUrlRepository;

    @Transactional
    public void save(YoutubeUrl youtubeUrl){
        youtubeUrlRepository.save(youtubeUrl);
    }

    /**
     * URL_COUNT만큼의 사이즈를 가진 Map<String, String>을 반환한다.
     * "url1" , "http://youtube..."
     * "url2" , "http://youtube..." 형식임.
     */
    public Map<String, String> getRandomUrlMap(int urlCount, String prefix) {
        List<YoutubeUrl> youtubeUrls = findAll();
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        Set<Integer> set = new HashSet<>();
        // 난수 중복 제거를 위한 set 사용
        while (set.size() < urlCount){
            /**
             *  ===Set.add()===
             *  If this set already contains the element, the call leaves the set unchanged and returns false. 중복원소 무시됨
             *  ==========
             */
            set.add(rand.nextInt(youtubeUrls.size()));
        }

        List<Integer> integerList= new ArrayList<>(set);

        Map<String, String > returnMap = new HashMap<>();

        for (int i = 0; i<urlCount; i+=1){
            returnMap.put("url" + (i+1), prefix+youtubeUrls.get(integerList.get(i)).getUrl());
        }
        return returnMap;
    }

    public List<YoutubeUrl> findAll() {
        return youtubeUrlRepository.findAll();
    }

}
