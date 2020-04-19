package com.gilxyj.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: chapter03
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-19 01:33
 **/

@Entity
@Table(name = "T_MENU")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Columns(columns = { @Column(name = "amount"), @Column(name = "currency") })
    @Type(type="org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency")
    private Money price;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateTime;

}
