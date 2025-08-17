using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface ICategoryService
    {
        Task<IEnumerable<CategoryDTO>> GetAllCategoriesAsync();
        Task<CategoryDTO?> GetCategoryByIdAsync(int id);
        Task<CategoryDTO> CreateCategoryAsync(CategoryDTO categoryDTO);
        Task<CategoryDTO?> UpdateCategoryAsync(int id, CategoryDTO categoryDTO);
        Task<bool> DeleteCategoryAsync(int id);

        Task<IEnumerable<PackageDTO>> GetPackagesByCategoryAsync(int categoryId);
    }
}
