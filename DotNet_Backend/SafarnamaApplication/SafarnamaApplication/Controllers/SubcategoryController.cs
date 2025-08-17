using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SubcategoryController : ControllerBase
    {
        private readonly ISubcategoryService _subcategoryService;

        public SubcategoryController(ISubcategoryService subcategoryService)
        {
            _subcategoryService = subcategoryService;
        }

        // Public: anyone can call this
        [HttpGet]
        [AllowAnonymous]
        public async Task<ActionResult<IEnumerable<SubcategoryDTO>>> GetAll()
        {
            var list = await _subcategoryService.GetAllSubcategoriesAsync();
            return Ok(list);
        }

        // Protected: requires JWT (Authorization header: Bearer <token>)
        [HttpGet("category/{categoryId}")]
        [Authorize]
        public async Task<ActionResult<IEnumerable<SubcategoryDTO>>> GetByCategory(int categoryId)
        {
            var list = await _subcategoryService.GetByCategoryIdAsync(categoryId);
            return Ok(list);
        }
    }
}
