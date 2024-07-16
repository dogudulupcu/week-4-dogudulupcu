package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.entity.Campaign;
import com.patika.kredinbizdeservice.entity.dto.CampaignDTO;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignDTO save(Campaign campaign){
        campaignRepository.save(campaign);
        return prepareCampaignDTO(campaign);
    }

    public List<CampaignDTO> getAll(){
        return prepareCampaignListDTO(campaignRepository.findAll());
    }

    public CampaignDTO findByTitle(String title){
        Optional<Campaign> foundCampaign = campaignRepository.findByTitle(title);
        Campaign campaign = null;
        if(foundCampaign.isPresent()){
             campaign = foundCampaign.get();
        }
        return prepareCampaignDTO(campaign);

    }

    public CampaignDTO update(String title, Campaign campaign){
        Optional<Campaign> foundCampaign = campaignRepository.findByTitle(title);
        if(foundCampaign.isPresent()){
            foundCampaign.get().setContent(campaign.getContent());
            foundCampaign.get().setTitle(campaign.getTitle());
            foundCampaign.get().setCreateDate(campaign.getCreateDate());
            foundCampaign.get().setUpdateDate(campaign.getUpdateDate());
            foundCampaign.get().setDueDate(campaign.getDueDate());
            foundCampaign.get().setSector(campaign.getSector());

            campaignRepository.delete(campaign);
            campaignRepository.save(foundCampaign.get());

            return prepareCampaignDTO(foundCampaign.get());
        }
        return null;
    }

    public CampaignDTO delete(Campaign campaign){
        campaignRepository.delete(campaign);
        return prepareCampaignDTO(campaign);
    }

    public List<CampaignDTO> getAllAscendingCreationDate(){
        List<Campaign> campaigns = campaignRepository.findAll();
        campaigns.sort((c1,c2)->c2.getCreateDate().compareTo(c1.getCreateDate()));
        return prepareCampaignListDTO(campaigns);

    }



    public CampaignDTO prepareCampaignDTO(Campaign campaign){
        return CampaignDTO.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .content(campaign.getContent())
                .dueDate(campaign.getDueDate())
                .createDate(campaign.getCreateDate())
                .updateDate(campaign.getUpdateDate())
                .sector(campaign.getSector())
                .creditCardId(campaign.getCreditCard().getId())
                .build();
    }

    public List<CampaignDTO> prepareCampaignListDTO(List<Campaign> campaigns) {
        return campaigns.stream().map(this::prepareCampaignDTO).collect(Collectors.toList());
    }
}
