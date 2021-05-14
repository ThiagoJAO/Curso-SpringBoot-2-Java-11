package com.empresa.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.empresa.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "tb_order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private Long id;
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T'HH:mm:ss'Z'", timezone = "GMT" )
	private Instant moment;
	
	
	@ManyToOne // MUITOS PARA UM
	@JoinColumn (name = "client_id")
	private User client;
	
	
// INTEGER PRA DIZER EXPLICITAMENTE QUE ESTÁ GRAVANDO NUM BANCO DE DADOS
	private Integer orderStatus;
	
// Mapeando o id.order, pq no OrderItem eu tenho o id, e o id por sua vez é quem tem o pedido
// Coleção de OrderItens para que Order os acesse
// ORDER_ITEM tem OrderItemPK id por sua vez ORDER_ITEMPK 0 order	
	@OneToMany (mappedBy = "id.order")  
	private Set <OrderItem> items = new HashSet<>(); 
	
// Faz somente um set para itens pois é uma coleção 	
	public Set<OrderItem> getItems() {
		return items;
	}


	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus ,User client) {
		this.id = id;
		this.moment = moment;
		this.client = client;
		setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

		
	// CONVERTENDO OS ENUMS 
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		
		if (orderStatus != null) {
		this.orderStatus = orderStatus.getCode();
	    }
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	 
	
	

}
