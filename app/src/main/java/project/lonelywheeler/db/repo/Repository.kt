package project.lonelywheeler.db.repo

import project.lonelywheeler.db.service.ProductService
import project.lonelywheeler.db.service.UserService
import javax.inject.Inject

class Repository
@Inject
constructor(
    val userService: UserService,
    val productService: ProductService
) {
}