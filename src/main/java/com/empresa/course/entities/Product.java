package com.empresa.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.empresa.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	
/*  Usa-se o Set(Coleção do Java, representa um conjunto), para garanti que não vou ter um produto
 *  com mais de uma ocorrência da mesma categoria. O mesmo produto não pode ter a mesma categoria
 *  mais de uma vez. 
*/
	
// Coleções não podem se iniciar nulas, tem que ser instaciadas porém vazias.
// O Set é uma interface, não pode ser instanciada, HashSet é a classe(Exemplo List ArrayList).

	
	@ManyToMany // MUITOS PARA MUITOS
	@JoinTable (name = "tb_product_category", 
	joinColumns = @JoinColumn(name = "product_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	
// Mapeando o id.product, pq no OrderItem eu tenho o id, e o id por sua vez é quem tem o produto
// Coleção de OrderItens para que Product os acesse
// ORDER_ITEM tem OrderItemPK id por sua vez ORDER_ITEMPK product	
	@OneToMany (mappedBy = "id.product")   
	private Set <OrderItem> items = new HashSet<>(); 
	
	
// MÉTODO GET, Ele vai responder pra mim uma lista de order e não de OrderItem conforme diagrama
// Varrer a colação de OrderIten e pegar o order associado a ele	
	
	@JsonIgnore
	public Set<Order> getOrders(){ // orders: nome projetado no diagrama
		
		Set<Order> set = new HashSet<>();
// Percorrendo a coleção de itens aqui de cima e para cada elemento dessa coleção que 
// dei o nome de x eu vou adcionar no meu conjunto o x.getOrder aí eu pego o objeto order
// associado ao objeto orderItem
		
		for(OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}
	
	
	public Product() {
	}

	// Não se coloca coleção em construtor. Ela já está instanciada lá em cima.
	
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
