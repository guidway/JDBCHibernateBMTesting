package com.guid.dbperformtests.services;

import com.guid.dbperformtests.models.HBMillion;
import com.guid.dbperformtests.repositories.HBMillionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

@Service("hbMillionService")
public class HBMillionService {
  @Autowired
  private HBMillionRepository hbMillionRepository;

  public List<HBMillion> getAllMembers() {
    return hbMillionRepository.findAll();
  }

  public HBMillion saveMember(HBMillion member) {
    return member;
  }

  @Transactional
  public void saveMembers() {

    int batchSize = 1000;
    Random rand = new Random();
    List<HBMillion> records = new ArrayList<>();
    for (int i = 0; i < 1000000; i++) {
      HBMillion toSave = new HBMillion();
      toSave.setId((long) i);
      toSave.setSome_value1("new_value" + i);
      toSave.setSome_value2("new_value" + i);
      toSave.setSome_value3("new_value" + i);
      toSave.setSome_value4("new_value" + i);
      toSave.setSome_value5(rand.nextLong());
      toSave.setSome_value6("new_value" + i);
      toSave.setSome_value7("new_value" + i);
      toSave.setSome_value8("new_value" + i);
      toSave.setSome_value9("new_value" + i);
      toSave.setSome_value10(rand.nextDouble());
      toSave.setSome_value11("new_value" + i);
      toSave.setSome_value12("new_value" + i);
      toSave.setSome_value13("new_value" + i);
      toSave.setSome_value14("new_value" + i);
      toSave.setSome_value15("new_value" + i);
      toSave.setSome_value16("new_value" + i);
      toSave.setSome_value17("new_value" + i);
      toSave.setSome_value18("new_value" + i);
      toSave.setSome_value19("new_value" + i);
      toSave.setSome_value20("new_value" + i);
      records.add(toSave);
      if (i % batchSize == 0) {
        hbMillionRepository.saveAll(records);
        records.clear();
      }
    }
    hbMillionRepository.saveAll(records);
    records.clear();
  }

  public HBMillion updateMember(HBMillion member, Long id) {
    HBMillion updateMember = hbMillionRepository.findById(id).orElse(null);
    System.out.println("UpdateMember: " + updateMember);
    if (updateMember != null) {
      if (member.getId() != null)
        updateMember.setId(member.getId());
      if (member.getSome_value1() != null)
        updateMember.setSome_value1(member.getSome_value1());
      if (member.getSome_value2() != null)
        updateMember.setSome_value2(member.getSome_value2());
      if (member.getSome_value3() != null)
        updateMember.setSome_value3(member.getSome_value3());
      if (member.getSome_value4() != null)
        updateMember.setSome_value4(member.getSome_value4());
      if (member.getSome_value5() != null)
        updateMember.setSome_value5(member.getSome_value5());
      if (member.getSome_value6() != null)
        updateMember.setSome_value6(member.getSome_value6());
      if (member.getSome_value7() != null)
        updateMember.setSome_value7(member.getSome_value7());
      if (member.getSome_value8() != null)
        updateMember.setSome_value8(member.getSome_value8());
      if (member.getSome_value9() != null)
        updateMember.setSome_value9(member.getSome_value9());
      if (member.getSome_value10() != null)
        updateMember.setSome_value10(member.getSome_value10());
      if (member.getSome_value11() != null)
        updateMember.setSome_value11(member.getSome_value11());
      if (member.getSome_value12() != null)
        updateMember.setSome_value12(member.getSome_value12());
      if (member.getSome_value13() != null)
        updateMember.setSome_value13(member.getSome_value13());
      if (member.getSome_value14() != null)
        updateMember.setSome_value14(member.getSome_value14());
      if (member.getSome_value15() != null)
        updateMember.setSome_value15(member.getSome_value15());
      if (member.getSome_value16() != null)
        updateMember.setSome_value16(member.getSome_value16());
      if (member.getSome_value17() != null)
        updateMember.setSome_value17(member.getSome_value17());
      if (member.getSome_value18() != null)
        updateMember.setSome_value18(member.getSome_value18());
      if (member.getSome_value19() != null)
        updateMember.setSome_value19(member.getSome_value19());
      if (member.getSome_value20() != null)
        updateMember.setSome_value20(member.getSome_value20());
    }
    System.out.println("updateMember: " + updateMember);
    final HBMillion mymember = hbMillionRepository.save(updateMember);
    return mymember;
  }

  public Boolean deleteMember(Long id) {
    HBMillion delMember = hbMillionRepository.findById(id).orElse(null);
    if (delMember != null) {
      hbMillionRepository.delete(delMember);
      return true;
    }
    return false;
  }
}