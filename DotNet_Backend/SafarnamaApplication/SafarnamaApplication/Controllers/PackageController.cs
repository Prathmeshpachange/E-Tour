using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PackageController : ControllerBase
    {
        private readonly IPackageService _packageService;

        public PackageController(IPackageService packageService)
        {
            _packageService = packageService;
        }

        // GET: api/package
        [HttpGet]
        public async Task<ActionResult<IEnumerable<PackageDTO>>> GetAll()
        {
            var packages = await _packageService.GetAllPackages();
            return Ok(packages);
        }

        // GET: api/package/{id}
        [HttpGet("{id}")]
        public async Task<ActionResult<PackageDTO>> GetById(int id)
        {
            var package = await _packageService.GetPackageById(id);
            if (package == null)
                return NotFound();

            return Ok(package);
        }

        // POST: api/package
        [HttpPost]
        public async Task<ActionResult> Create([FromBody] PackageDTO packageDto)
        {
            await _packageService.AddPackage(packageDto);
            return CreatedAtAction(nameof(GetById), new { id = packageDto.PackageId }, packageDto);
        }

        // PUT: api/package/{id}
        [HttpPut("{id}")]
        public async Task<ActionResult> Update(int id, [FromBody] PackageDTO packageDto)
        {
            if (id != packageDto.PackageId)
                return BadRequest("Package ID mismatch");

            await _packageService.UpdatePackage(packageDto);
            return NoContent();
        }

        // DELETE: api/package/{id}
        [HttpDelete("{id}")]
        public async Task<ActionResult> Delete(int id)
        {
            var exists = await _packageService.PackageExists(id);
            if (!exists)
                return NotFound();

            await _packageService.DeletePackage(id);
            return NoContent();
        }

        [HttpGet("subcategory/{subcategoryId}")]
        public async Task<ActionResult<IEnumerable<PackageDTO>>> GetBySubcategory(int subcategoryId)
        {
            return Ok(await _packageService.GetPackagesBySubcategoryIdAsync(subcategoryId));
        }
    }

}
