const products = [
    {
        id: 1,
        name: "Product 1",
        price: 1000,
        brand: "Sony",
        thumbnail: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D",
        rating: 4.5
    },
    {
        id: 2,
        name: "Product 2",
        price: 2000,
        brand: "LG",
        thumbnail: "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D",
        rating: 4.5
    },
    {
        id: 3,
        name: "Product 3",
        price: 3000,
        brand: "Samsung",
        thumbnail: "https://images.unsplash.com/photo-1581235720704-06d3acfcb36f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D",
        rating: 4.5
    },
    {
        id: 4,
        name: "Product 4",
        price: 4000,
        brand: "Panasonic",
        thumbnail: "https://images.unsplash.com/photo-1581235720704-06d3acfcb36f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D",
        rating: 4.5
    },
    {
        id: 5,
        name: "Product 5",
        price: 5000,
        brand: "HP",
        thumbnail: "https://images.unsplash.com/photo-1532298229144-0ec0c57515c7?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzR8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D",
        rating: 4.5
    },
    {
        id: 6,
        name: "Product 6",
        price: 6000,
        brand: "Apple",
        thumbnail: "https://images.unsplash.com/photo-1585565804112-f201f68c48b4?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nzl8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D",
        rating: 4.5
    }
]

// format number to vnd with comma
const formatNumber = (number) => {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// render products
const productListEl = document.querySelector(".product-list");
const renderProducts = (products) => {
    let html = "";

    products.forEach(product => {
        html += `
            <div class="col-md-3">
                <div class="course-item shadow-sm rounded mb-4">
                    <div class="course-item-image">
                        <img src="${product.thumbnail}" alt="${product.name}">
                    </div>
                    <div class="course-item-info p-3">
                        <h2 class="fs-5 mb-4 text-dark">
                            ${product.name}
                        </h2>
                        <div class="d-flex justify-content-between align-items-center fw-light text-black-50">
                            <p class="type">${product.brand}</p>
                            <p class="rating">
                                <span>${product.rating}</span>
                                <span class="text-warning"><i class="fa-solid fa-star"></i></span>
                            </p>
                        </div>
                        <p class="price text-danger fs-5">
                            ${formatNumber(product.price)} đ
                        </p>
                    </div>
                </div>
            </div>
        `
    })

    productListEl.innerHTML = html;
}

renderProducts(products);