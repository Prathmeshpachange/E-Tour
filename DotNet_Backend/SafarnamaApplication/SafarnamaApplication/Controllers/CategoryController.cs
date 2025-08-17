using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoryController : ControllerBase
    {
        private readonly ICategoryService _categoryService;

        public CategoryController(ICategoryService categoryService)
        {
            _categoryService = categoryService;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<CategoryDTO>>> GetAll()
        {
            return Ok(await _categoryService.GetAllCategoriesAsync());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CategoryDTO>> GetById(int id)
        {
            var category = await _categoryService.GetCategoryByIdAsync(id);
            return category == null ? NotFound() : Ok(category);
        }

        [HttpPost]
        public async Task<ActionResult<CategoryDTO>> Create(CategoryDTO dto)
        {
            var created = await _categoryService.CreateCategoryAsync(dto);
            return Ok(created);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<CategoryDTO>> Update(int id, CategoryDTO dto)
        {
            var updated = await _categoryService.UpdateCategoryAsync(id, dto);
            return updated == null ? NotFound() : Ok(updated);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var deleted = await _categoryService.DeleteCategoryAsync(id);
            return deleted ? Ok("Deleted Successfully") : NotFound();
        }

        [HttpGet("{categoryId}/packages")]
        public async Task<ActionResult<IEnumerable<PackageDTO>>> GetPackagesByCategory(int categoryId)
        {
            var packages = await _categoryService.GetPackagesByCategoryAsync(categoryId);
            if (!packages.Any())
                return NotFound();

            return Ok(packages);
        }
    }
}
