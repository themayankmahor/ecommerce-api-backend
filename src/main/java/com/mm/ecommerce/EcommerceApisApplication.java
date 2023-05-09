package com.mm.ecommerce;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mm.ecommerce.config.AppConstants;
import com.mm.ecommerce.entity.Role;
import com.mm.ecommerce.repository.RoleRepository;

@SpringBootApplication
public class EcommerceApisApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			///Master
			Role  roleMaster = new Role();
			roleMaster.setId(AppConstants.MASTER_USER);
			roleMaster.setName("ROLE_MASTER");

			///Admin
			Role  roleAdmin = new Role();
			roleAdmin.setId(AppConstants.ADMIN_USER);
			roleAdmin.setName("ROLE_ADMIN");

			///User
			Role  roleNormal = new Role();
			roleNormal.setId(AppConstants.NORMAL_USER);
			roleNormal.setName("ROLE_NORMAL");

			///Seller
			Role roleSeller = new Role();
			roleSeller.setId(AppConstants.SELLER_USER);
			roleSeller.setName("ROLE_SELLER");

			List<Role>  roles = List.of(roleMaster, roleAdmin, roleNormal, roleSeller);

			List<Role> result = roleRepository.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
