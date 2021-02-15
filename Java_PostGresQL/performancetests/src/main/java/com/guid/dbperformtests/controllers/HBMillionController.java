package com.guid.dbperformtests.controllers;

import com.guid.dbperformtests.JDBCTest;
import com.guid.dbperformtests.models.HBMillion;
import com.guid.dbperformtests.services.HBMillionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class HBMillionController {
  @Autowired
  private HBMillionService memberService;

  @GetMapping("/members")
  public List<HBMillion> all() {

    JDBCTest jdbcTest = new JDBCTest();
    try {

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
      Date startDate = new Date(System.currentTimeMillis());
      System.out.println("Start Get All JDBC TEST! " + formatter.format(startDate));
      int records = jdbcTest.fetchAllData();
      System.out.println("Records read: " + records);
      Date endDate = new Date(System.currentTimeMillis());
      System.out.println("Get All JDBC Complete! " + formatter.format(endDate));
      float diff = endDate.getTime() - startDate.getTime();
      System.out.println("Time taken (ms): " + diff + " (sec) " + diff / 1000);
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      jdbcTest.disconnect();
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date startDate = new Date(System.currentTimeMillis());
    System.out.println("Start Get All Hibernate TEST! " + formatter.format(startDate));
    List<HBMillion> members = memberService.getAllMembers();
    System.out.println("Records read: " + members.size());
    Date endDate = new Date(System.currentTimeMillis());
    System.out.println("Get All Hibernate Complete! " + formatter.format(endDate));
    float diff = endDate.getTime() - startDate.getTime();
    System.out.println("Time taken (ms): " + diff + " (sec) " + diff / 1000);

    return members;
  }

  @PostMapping("/members")
  public ResponseEntity<HBMillion> createMember(@Valid @RequestBody HBMillion member) {

    // Runs the JDBC Test
    JDBCTest jdbcTest = new JDBCTest();
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
      Date startDate = new Date(System.currentTimeMillis());
      System.out.println("Create Start JDBC TEST! " + formatter.format(startDate));
      if (!jdbcTest.tableExists())
        jdbcTest.createTable();
      jdbcTest.oneMillionBatchRecordInsertTest();

      Date endDate = new Date(System.currentTimeMillis());
      System.out.println("Create JDBC TEST Complete! " + formatter.format(endDate));
      float diff = endDate.getTime() - startDate.getTime();
      System.out.println("Time taken (ms): " + diff + " (sec) " + diff / 1000);

    } catch (Exception e) {
      System.out.println(e);
    } finally {
      jdbcTest.disconnect();
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date startDate = new Date(System.currentTimeMillis());
    System.out.println("Create Start Hibernate TEST! " + formatter.format(startDate));

    // Runs the Hibernate Test
    memberService.saveMembers();

    Date endDate = new Date(System.currentTimeMillis());
    System.out.println("Create Hibernate TEST Complete! " + formatter.format(endDate));
    float diff = endDate.getTime() - startDate.getTime();
    System.out.println("Time taken (ms): " + diff + " (sec) " + diff / 1000);
    return ResponseEntity.ok(member);
  }

  @PutMapping("/members/{id}")
  public ResponseEntity<HBMillion> updateMember(@Valid @RequestBody HBMillion member,
      @PathVariable(value = "id") Long id) {
    System.out.println("id: " + id + " member: " + member);
    return ResponseEntity.ok(memberService.updateMember(member, id));
  }

  @DeleteMapping("/members/{id}")
  public ResponseEntity<?> deleteMemeber(@PathVariable Long id) {
    Map<String, String> response = new HashMap<String, String>();
    if (memberService.deleteMember(id)) {
      response.put("status", "success");
      response.put("message", "member deleted successfully");
      return ResponseEntity.ok(response);
    } else {
      response.put("status", "error");
      response.put("message", "Somthing went wrong when delete the member");
      return ResponseEntity.status(500).body(response);
    }
  }
}